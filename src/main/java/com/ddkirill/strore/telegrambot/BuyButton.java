package com.ddkirill.strore.telegrambot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyButton {

    public InlineKeyboardMarkup getBuyKeyboard() {

        InlineKeyboardButton buyBotton = new InlineKeyboardButton();
        buyBotton.setText("Buy");
        buyBotton.setCallbackData("buy");

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(buyBotton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(firstRow);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;

    }
}
