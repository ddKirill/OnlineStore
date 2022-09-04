package com.ddkirill.strore.telegrambot.keyboards;

import com.ddkirill.strore.enums.ButtonNameEnum;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyProductButton {

    public InlineKeyboardMarkup getBuyKeyboard(int productPrice, Long productId) {

        InlineKeyboardButton buyButton = new InlineKeyboardButton();
        buyButton.setText("Buy - " + productPrice + "₽");
        buyButton.setCallbackData(productId.toString());

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(buyButton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(firstRow);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;

    }

    public InlineKeyboardMarkup mainMenu() {
        InlineKeyboardButton mainMenu = new InlineKeyboardButton();
        mainMenu.setText(ButtonNameEnum.MAIN_MENU.getButtonName());
        mainMenu.setCallbackData("/mainMenu");

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(mainMenu);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(firstRow);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

         return inlineKeyboardMarkup;
    }


}
