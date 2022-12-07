package com.ddkirill.strore.enums;

public enum TextEnum {

    WRITE_PRODUCT_TITLE("Введите наименование товара ->");

    private final String textPattern;

    TextEnum(String textPattern) {
        this.textPattern = textPattern;
    }

    public String getTextPattern() {
        return textPattern;
    }
}
