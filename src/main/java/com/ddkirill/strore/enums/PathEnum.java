package com.ddkirill.strore.enums;

public enum PathEnum {
    HELP_TEXT("text/helpText.txt"),
    NEWS_TEXT("text/newsText.txt"),
    START_TEXT("text/startText.txt"),
    START_IMAGE("text/image.jpg"),
    NON_COMMAND_TEXT("Message is not a command\nPlease view Help menu /help");

    private final String pathName;

    PathEnum(String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }
}
