package com.ddkirill.strore.telegrambot.keyboards;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminButton {


    public InlineKeyboardMarkup getMainMenuButton() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton addButton = new InlineKeyboardButton();
        InlineKeyboardButton deleteButton = new InlineKeyboardButton();
        InlineKeyboardButton updateButton = new InlineKeyboardButton();
        InlineKeyboardButton addModerator = new InlineKeyboardButton();
        InlineKeyboardButton addHelp = new InlineKeyboardButton();

        addButton.setCallbackData("/addProducts");
        addButton.setText("Добавление новых товаров в каталог.");
        deleteButton.setCallbackData("/deleteProduct");
        deleteButton.setText("Удаление имеющихся товаров.");
        updateButton.setCallbackData("/updateProduct");
        updateButton.setText("Редактирование товара");
        addModerator.setCallbackData("/setModerator");
        addModerator.setText("Назначение роли модератора.");
        addHelp.setCallbackData("/addHelp");
        addHelp.setText("Добавление новостей в HELP.");

        List<InlineKeyboardButton>  addButtonRow = new ArrayList<>();
        addButtonRow.add(addButton);

        List<InlineKeyboardButton>  deleteButtonRow = new ArrayList<>();
        deleteButtonRow.add(deleteButton);

        List<InlineKeyboardButton>  updateRow = new ArrayList<>();
        updateRow.add(updateButton);

        List<InlineKeyboardButton> addModeratorRow = new ArrayList<>();
        addModeratorRow.add(addModerator);

        List<InlineKeyboardButton> addHelpRow = new ArrayList<>();
        addHelpRow.add(addHelp);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(addButtonRow);
        rowList.add(deleteButtonRow);
        rowList.add(updateRow);
        rowList.add(addModeratorRow);
        rowList.add(addHelpRow);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;



    }
}
