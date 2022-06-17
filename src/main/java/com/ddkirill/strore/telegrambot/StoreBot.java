package com.ddkirill.strore.telegrambot;

import com.ddkirill.strore.config.BotProperties;
import com.ddkirill.strore.domain.AllProducts;
import com.ddkirill.strore.domain.AllProductsWithFullInformation;
import com.ddkirill.strore.service.ReadTxt;
import com.ddkirill.strore.service.products.GetAllProducts;
import com.ddkirill.strore.service.products.GetAllProductsWithFullInformation;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;


@Component
public class StoreBot {

    private final BotProperties botProperties;
    private final TelegramLongPollingBot bot;
    private final TelegramBotsApi botsApi;
    private final GetAllProducts getAllProducts;
    private final GetAllProductsWithFullInformation getAllProductsWithFullInformation;
    private ReadTxt readTxt;

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
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(readTxt.readTextFile("text/startText.txt"));

                        sendTextMessage(chat.getId(), stringBuilder.toString());
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
