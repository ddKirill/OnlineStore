package com.ddkirill.strore.telegrambot;

import com.ddkirill.strore.config.BotProperties;
import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.enums.PathEnum;
import com.ddkirill.strore.model.Product;
import com.ddkirill.strore.service.CartManageService;
import com.ddkirill.strore.service.OrderManagerService;
import com.ddkirill.strore.service.ReadTxt;
import com.ddkirill.strore.service.UserManagerService;
import com.ddkirill.strore.service.products.ProductManageService;
import com.ddkirill.strore.telegrambot.keyboards.BuyProductButton;
import com.ddkirill.strore.telegrambot.keyboards.InlineKeyboardStart;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.List;


@Component
public class StoreBot {

    private final BotProperties botProperties;
    private final TelegramLongPollingBot bot;
    private final TelegramBotsApi botsApi;
    private final ReadTxt readTxt;
    private final UserManagerService userManagerService;
    private final OrderManagerService orderManagerService;
    private final ProductManageService productManageService;
    private final CartManageService cartManageService;

    public StoreBot(BotProperties botProperties, ReadTxt readTxt,
                    UserManagerService userManagerService, OrderManagerService orderManagerService, ProductManageService productManageService, CartManageService cartManageService) throws TelegramApiException {
        this.botProperties = botProperties;
        this.readTxt = readTxt;
        this.userManagerService = userManagerService;
        this.orderManagerService = orderManagerService;
        this.productManageService = productManageService;
        this.cartManageService = cartManageService;
        this.botsApi = new TelegramBotsApi(DefaultBotSession.class);
        this.bot = new MyBot();
        this.botsApi.registerBot(bot);
    }

    class MyBot extends TelegramLongPollingBot {

        @Override
        public String getBotUsername() {
            return botProperties.bot().username();
        }

        @Override
        public String getBotToken() {
            return botProperties.bot().token();
        }

        @Override
        public void onUpdateReceived(Update update) {

            if (update.hasMessage()) {
                Message message = update.getMessage();
                Chat chat = message.getChat();

                //CommandHandlers
                if (message.isCommand()) {

                    if ("/start".equals(message.getText())) {
                        userManagerService.addNewUser(message);
                        sendPhotoCaptionKeyboard(chat.getId().toString(), new InputFile(new File(PathEnum.START_IMAGE.getPathName()))
                                , readTxt.readTextFile(PathEnum.START_TEXT.getPathName()), new InlineKeyboardStart().getStartKeyboard());
                    }
                }
                //NonCommandHandler
                else if (message.isUserMessage()) {
                    sendTextMessage(chat.getId(), PathEnum.NON_COMMAND_TEXT.getPathName());
                }
            }

            if (update.hasCallbackQuery()) {
                String callData = update.getCallbackQuery().getData();
                long chatId = update.getCallbackQuery().getMessage().getChatId();
                OrderEntity currentOrder = orderManagerService.getCurrentOrder(chatId);
                List<String> productIdList = productManageService.getProductIdList();

                if (callData.equals("/help")) {
                    sendTextMessage(chatId, readTxt.readTextFile(PathEnum.HELP_TEXT.getPathName()));
                }

                if (callData.equals("/news")) {
                    sendTextMessageAndKeyboard(Long.valueOf(String.valueOf(chatId)), readTxt.readTextFile(PathEnum.NEWS_TEXT.getPathName()), new BuyProductButton().mainMenu());
                }

                if (callData.equals("/mainMenu")) {
                    sendPhotoCaptionKeyboard(String.valueOf(chatId), new InputFile(new File(PathEnum.START_IMAGE.getPathName()))
                            , readTxt.readTextFile(PathEnum.START_TEXT.getPathName()), new InlineKeyboardStart().getStartKeyboard());
                }

                if (callData.equals("/allProducts")) {
                    sendTextMessage(chatId, "Все товары:");

                    List<Product> allProducts = productManageService.getAllProducts();

                    for (Product allProduct : allProducts) {

                        String title = allProduct.getTitle() + "\n";
                        String description = allProduct.getDescription() + "\n";
                        Long productId = allProduct.getId();

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(title);
                        stringBuilder.append(description);

                        sendPhotoCaptionKeyboard(String.valueOf(chatId), new InputFile(new File(allProduct.getLocationImage()))
                                , stringBuilder.toString(), new BuyProductButton().getBuyKeyboard(allProduct.getPrice(), productId));
                    }
                    sendTextMessageAndKeyboard(chatId, "Показаны все товары!", new BuyProductButton().mainMenu());
                }

                if (productIdList.contains(callData)){
                    orderManagerService.addProductInOrder(currentOrder.getOrderNumber(), Long.valueOf(callData));
                }

                if (callData.equals("/cart")) {
                    String viewCurrentOrder = cartManageService.viewCurrentOrder(currentOrder);
                    sendTextMessage(chatId, viewCurrentOrder);
                }

            }
        }

        //This method execute send message to user
        private void sendTextMessage(Long chatId, String text) {
            try {

                execute(
                        SendMessage.builder()
                                .text(text)
                                .chatId(chatId.toString())
                                .build()

                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void sendTextMessageAndKeyboard(Long chatId, String text, InlineKeyboardMarkup keyboard) {
            try {
                execute(SendMessage.builder()
                        .text(text)
                        .chatId(chatId.toString())
                        .replyMarkup(keyboard)
                        .build()
                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        private void sendPhotoCaptionKeyboard(String chatId, InputFile photo, String caption, InlineKeyboardMarkup keyboard) {
            try {
                execute(SendPhoto.builder()
                        .chatId(chatId)
                        .photo(photo)
                        .caption(caption)
                        .replyMarkup(keyboard)
                        .build()
                );
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}