package com.ddkirill.strore.telegrambot;

import com.ddkirill.strore.config.BotProperties;
import com.ddkirill.strore.domain.Product;
import com.ddkirill.strore.service.ReadTxt;
import com.ddkirill.strore.service.products.GetAllProducts;
import com.ddkirill.strore.service.products.GetAllProductsWithFullInformation;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.List;

import static java.lang.Math.toIntExact;


@Component
public class StoreBot {

    private final BotProperties botProperties;
    private final TelegramLongPollingBot bot;
    private final TelegramBotsApi botsApi;
    private final GetAllProducts getAllProducts;
    private final GetAllProductsWithFullInformation getAllProductsWithFullInformation;
    private final ReadTxt readTxt;


    public StoreBot(BotProperties botProperties, GetAllProducts getAllProducts, GetAllProductsWithFullInformation getAllProductsWithFullInformation, ReadTxt readTxt) throws TelegramApiException {
        this.botProperties = botProperties;
        this.getAllProducts = getAllProducts;
        this.getAllProductsWithFullInformation = getAllProductsWithFullInformation;
        this.readTxt = readTxt;
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
                            String START_TEXT = "text/startText.txt";
                            //Send Photo, description, buttons to chat
                            SendPhoto sendPhoto = new SendPhoto();
                            sendPhoto.setChatId(chat.getId().toString());
                            sendPhoto.setPhoto(new InputFile(new File("text/image.jpg")));
                            sendPhoto.setReplyMarkup(new InlineKeyboardStart().getStartKeyboard());
                            sendPhoto.setCaption(readTxt.readTextFile(START_TEXT));

                            try {
                                execute(sendPhoto);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }

                        if ("/allProducts".equals(message.getText())) {
                            List<Product> allProducts = getAllProducts.getAllProducts();

                            for (Product allProduct : allProducts) {
//
                                String s = allProduct.getPrice() + "₽";
                                String string = allProduct.getTitle() + "\n" + s;
                                sendTextMessage(chat.getId(),string);
                            }

                        }

                        if ("/manageProducts".equals(message.getText())) {
                            List<Product> allProductsList = getAllProductsWithFullInformation.getAllProducts();

                            for (Product products : allProductsList) {
                                SendPhoto sendPhotoProduct = new SendPhoto();
                                String price = products.getPrice() +"₽";
                                String string = products.getTitle()
                                        + "\n"
                                        + price
                                        + "\n"
                                        + products.getDescription();
                                sendPhotoProduct.setChatId(update.getMessage().getChatId().toString());
                                sendPhotoProduct.setPhoto(new InputFile(new File(products.getLocationImage())));
                                try {
                                    execute(sendPhotoProduct);
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                                sendTextMessage(chat.getId(),string);
                            }

                        }

                        if ("/help".equals(message.getText())) {
                            String HELP_TEXT = "text/helpText.txt";
                            sendTextMessage(chat.getId(), readTxt.readTextFile(HELP_TEXT));
                        }

                    }

                    //NonCommandHandler
                    else if (message.isUserMessage()) {
                        sendTextMessage(chat.getId(), "Message is not a command\nPlease view Help menu /help");
                    }
                }
            else if (update.hasCallbackQuery()) {
                String callData = update.getCallbackQuery().getData();
                long chatId = update.getCallbackQuery().getMessage().getChatId();
                long messageId = update.getCallbackQuery().getMessage().getMessageId();

                if (callData.equals("news")) {
                    String NEWS = "text/newsText.txt";
                    EditMessageText editMessage = new EditMessageText();
                    editMessage.setText(NEWS);
                    editMessage.setChatId(String.valueOf(chatId));
                    editMessage.setMessageId(toIntExact(messageId));
                    try {
                        execute(editMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

            //This method execute send message to user
            private void sendTextMessage (Long chatId, String text){
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

        }

    }

