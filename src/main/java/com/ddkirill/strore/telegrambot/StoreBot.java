package com.ddkirill.strore.telegrambot;

import com.ddkirill.strore.config.BotProperties;
import com.ddkirill.strore.domain.AllProducts;
import com.ddkirill.strore.domain.AllProductsWithFullInformation;
import com.ddkirill.strore.service.ReadTxt;
import com.ddkirill.strore.service.products.GetAllProducts;
import com.ddkirill.strore.service.products.GetAllProductsWithFullInformation;
import org.glassfish.grizzly.nio.transport.DefaultStreamReader;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

                if (message.isCommand()) {

                    if ("/start".equals(message.getText())) {
                        //Send text by using ReadText service
                        StringBuilder stringBuilder = new StringBuilder();
                        String START_TEXT = "text/startText.txt";
                        stringBuilder.append(readTxt.readTextFile(START_TEXT));
                        sendTextMessage(chat.getId(), stringBuilder.toString());
                        //Send Photo to chat
                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setChatId(chat.getId().toString());
                        sendPhoto.setPhoto(new InputFile(new File("text/Image1.jpg")));




                        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
                        inlineKeyboardButton1.setText("Information");
                        inlineKeyboardButton1.setUrl("https://github.com/ddKirill/OnlineStore");
                        inlineKeyboardButton2.setText("All products");
                        inlineKeyboardButton2.setCallbackData("bla bla bla");

                        List<InlineKeyboardButton> keyboardButtonList = new ArrayList<>();
                        keyboardButtonList.add(inlineKeyboardButton1);
                        keyboardButtonList.add(inlineKeyboardButton2);

                        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                        rowList.add(keyboardButtonList);

                        inlineKeyboardMarkup.setKeyboard(rowList);
                        sendPhoto.setReplyMarkup(inlineKeyboardMarkup);

                        try {
                            execute(sendPhoto);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    }



                    if ("/allProducts".equals(message.getText())) {
                        List<AllProducts> allProducts = getAllProducts.getAllProducts();
                        StringBuilder stringBuilder = new StringBuilder();

                        for (AllProducts allProduct : allProducts) {
                            stringBuilder.append(allProduct.getTitle())
                                    .append(" ")
                                    .append(allProduct.getPrice())
                                    .append("₽")
                                    .append("\n");
                        }
                        sendTextMessage(chat.getId(), stringBuilder.toString());
                    }

                    if ("/manageProducts".equals(message.getText())) {
                        List<AllProductsWithFullInformation> allProductsList = getAllProductsWithFullInformation.getAllProducts();
                        StringBuilder stringBuilder = new StringBuilder();

                        for (AllProductsWithFullInformation products : allProductsList) {
                            stringBuilder.append(products.getTitle())
                                    .append(" ")
                                    .append(products.getPrice())
                                    .append(" ")
                                    .append(products.getDescription())
                                    .append(" ")
                                    .append(products.getLocationImage())
                                    .append("\n");
                        }
                        sendTextMessage(chat.getId(), stringBuilder.toString());
                    }
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

    }

}
