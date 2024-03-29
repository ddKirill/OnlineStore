package com.ddkirill.strore.enums;

public enum ButtonNameEnum {
    INFORMATION("Информация"),
    ALL_PRODUCTS("Каталог товаров"),
    CART("Корзина"),
    ORDER_HISTORY("История заказов"),
    HELP("Help"),
    NEWS("Новости"),
    MAIN_MENU("Главное меню");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}
