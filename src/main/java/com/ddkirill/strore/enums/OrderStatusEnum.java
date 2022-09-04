package com.ddkirill.strore.enums;

public enum OrderStatusEnum {
    AWAITING_PAYMENT("Ожидает оплаты"),
    PAID("Оплачено");

    private final String status;

    OrderStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
