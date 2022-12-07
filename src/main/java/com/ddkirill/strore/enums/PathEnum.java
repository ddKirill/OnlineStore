package com.ddkirill.strore.enums;

public enum PathEnum {
    HELP_TEXT("text/HelpText.txt"),
    SETTINGS_TEXT("text/SettingsText.txt"),
    START_TEXT("text/StartText.txt"),
    NON_COMMAND_TEXT("Message is not a command\nPlease view Help menu /help"),

    ADMIN_PANEL_MAIN_MENU("text/AdminPanelMainMenu.txt"),
    START_IMAGE("image/StartImage.jpeg"),

    SETTINGS_IMAGE("image/SettingsImage.jpg");


    private final String pathName;

    PathEnum(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }
}
