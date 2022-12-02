package com.ddkirill.strore.enums;

public enum PathEnum {
    HELP_TEXT("text/HelpText.txt"),
    NEWS_TEXT("text/NewsText.txt"),
    START_TEXT("text/StartText.txt"),
    START_IMAGE("image/StartImage.jpeg"),
    NON_COMMAND_TEXT("Message is not a command\nPlease view Help menu /help");

    private final String pathName;

    PathEnum(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }
}
