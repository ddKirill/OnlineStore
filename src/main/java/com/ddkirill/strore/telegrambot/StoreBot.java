package com.ddkirill.strore.telegrambot;

import com.ddkirill.strore.config.BotProperties;
import com.ddkirill.strore.domain.Product;
import com.ddkirill.strore.repository.UserRepository;
import com.ddkirill.strore.service.ReadTxt;
import com.ddkirill.strore.service.UserService;
import com.ddkirill.strore.service.products.GetAllProducts;
import com.ddkirill.strore.telegrambot.enums.PathEnum;
import com.ddkirill.strore.telegrambot.keyboards.BuyProductButton;
import com.ddkirill.strore.telegrambot.keyboards.InlineKeyboardStart;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
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
    private final GetAllProducts getAllProducts;
    private final ReadTxt readTxt;
    private final UserService userService;

    public StoreBot(BotProperties botProperties, GetAllProducts getAllProducts, ReadTxt readTxt, UserRepository userRepository, UserService userService) throws TelegramApiException {
        this.botProperties = botProperties;
        this.getAllProducts = getAllProducts;
        this.readTxt = readTxt;
        this.userService = userService;
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
                User user = message.getFrom();

                //CommandHandlers
                if (message.isCommand()) {

                    if ("/start".equals(message.getText())) {

                        userService.addNewUser(message);
                        sendPhotoCaptionKeyboard(chat.getId().toString(), new InputFile(new File(PathEnum.START_IMAGE.getPathName()))
                                , readTxt.readTextFile(PathEnum.START_TEXT.getPathName()), new InlineKeyboardStart().getStartKeyboard());

                    }

                    if ("/allProducts".equals(message.getText())) {
                        sendTextMessage(chat.getId(), "All products: ");
                        List<Product> allProducts = getAllProducts.getAllProducts();

                        for (Product allProduct : allProducts) {

                            String title = allProduct.getTitle() + "\n";
                            String description = allProduct.getDescription() + "\n";

                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(title);
                            stringBuilder.append(description);

                            sendPhotoCaptionKeyboard(chat.getId().toString(), new InputFile(new File(allProduct.getLocationImage()))
                                    , stringBuilder.toString(), new BuyProductButton().getBuyKeyboard(allProduct.getPrice()));
                        }
                        sendTextMessageAndKeyboard(chat.getId(), "That's all products", new BuyProductButton().mainMenu());
                    }

                    if ("/help".equals(message.getText())) {
                        sendTextMessage(chat.getId(), readTxt.readTextFile(PathEnum.HELP_TEXT.getPathName()));
                    }
                }

                //NonCommandHandler
                else if (message.isUserMessage()) {
                    sendTextMessage(chat.getId(), PathEnum.NON_COMMAND_TEXT.getPathName());
                }
            } else if (update.hasCallbackQuery()) {
                String callData = update.getCallbackQuery().getData();
                long chatId = update.getCallbackQuery().getMessage().getChatId();
                long messageId = update.getCallbackQuery().getMessage().getMessageId();

                if (callData.equals("/help")) {
                    sendTextMessage(chatId, readTxt.readTextFile(PathEnum.HELP_TEXT.getPathName()));
                }

                if (callData.equals("/news")) {
                    sendTextMessageAndKeyboard(Long.valueOf(String.valueOf(chatId)), readTxt.readTextFile(PathEnum.NEWS_TEXT.getPathName()), new BuyProductButton().mainMenu());
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