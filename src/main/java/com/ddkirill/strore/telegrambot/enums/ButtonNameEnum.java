package com.ddkirill.strore.telegrambot.enums;

public enum ButtonNameEnum {
    INFORMATION("Information"),
    ALL_PRODUCTS("All products"),
    CART("Cart"),
    ORDER_HISTORY("Order history"),
    HELP("Help"),
    NEWS("News"),
    MAIN_MENU("Main menu"),
    BACK_BUTTON("Back");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
