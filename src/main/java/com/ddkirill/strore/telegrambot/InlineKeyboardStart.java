package com.ddkirill.strore.telegrambot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class InlineKeyboardStart {


    public InlineKeyboardMarkup getStartKeyboard() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonInformation = new InlineKeyboardButton();
        InlineKeyboardButton buttonAllProducts = new InlineKeyboardButton();
        InlineKeyboardButton buttonCart = new InlineKeyboardButton();
        InlineKeyboardButton orderHistoryButton = new InlineKeyboardButton();
        InlineKeyboardButton helpButton = new InlineKeyboardButton();
        InlineKeyboardButton newsButton = new InlineKeyboardButton();
        //Add configurations for buttons
        buttonInformation.setText("Information");
        buttonInformation.setUrl("https://github.com/ddKirill/OnlineStore");
        buttonAllProducts.setText("All products");
        buttonAllProducts.setCallbackData("bla bla bla");
        buttonCart.setText("Cart");
        buttonCart.setCallbackData("bla");
        orderHistoryButton.setText("OrderHistory");
        orderHistoryButton.setCallbackData("order History");
        helpButton.setText("Help");
        helpButton.setCallbackData("/help");
        newsButton.setText("News");
        newsButton.setCallbackData("news");

        //Create button's row
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(buttonInformation);
        firstRow.add(buttonAllProducts);


        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(buttonCart);
        secondRow.add(orderHistoryButton);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        thirdRow.add(helpButton);
        thirdRow.add(newsButton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(firstRow);
        rowList.add(secondRow);
        rowList.add(thirdRow);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
