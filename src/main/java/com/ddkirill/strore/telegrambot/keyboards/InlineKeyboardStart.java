package com.ddkirill.strore.telegrambot.keyboards;

import com.ddkirill.strore.enums.ButtonNameEnum;
import com.vdurmont.emoji.EmojiParser;
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
        buttonInformation.setText(textPlusEmoji(ButtonNameEnum.INFORMATION.getButtonName(), ":page_facing_up:"));
        buttonInformation.setUrl("https://github.com/ddKirill/OnlineStore");
        buttonAllProducts.setText(textPlusEmoji(ButtonNameEnum.ALL_PRODUCTS.getButtonName(), ":chart_with_upwards_trend:"));
        buttonAllProducts.setCallbackData("/allProducts");
        buttonCart.setText(textPlusEmoji(ButtonNameEnum.CART.getButtonName(), ":school_satchel:"));
        buttonCart.setCallbackData("/cart");
        orderHistoryButton.setText(textPlusEmoji(ButtonNameEnum.ORDER_HISTORY.getButtonName(), ":scroll:"));
        orderHistoryButton.setCallbackData("/orderHistory");
        helpButton.setText(textPlusEmoji(ButtonNameEnum.HELP.getButtonName(), ":sos:"));
        helpButton.setCallbackData("/help");
        newsButton.setText(textPlusEmoji(ButtonNameEnum.SETTINGS.getButtonName(), ":wrench:"));
        newsButton.setCallbackData("/settings");

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

    private String textPlusEmoji(String text, String emoji) {
        String emojiToString = EmojiParser.parseToUnicode(emoji);
        String textPlusEmoji = text + emojiToString;
        return textPlusEmoji;
    }

}
