package com.ddkirill.strore.domain;

import java.sql.Timestamp;

public class AllOrders {

    private final int number;
    private final String buyer;
    private final String productTitle;
    private final int amount;
    private final Timestamp orderTimes;
    private final int totalCost;

    public AllOrders(int number, String buyer, String productTitle, int amount, Timestamp orderTimes, int totalCost) {
        this.number = number;
        this.buyer = buyer;
        this.productTitle = productTitle;
        this.amount = amount;
        this.orderTimes = orderTimes;
        this.totalCost = totalCost;
    }

    public int getNumber() {
        return number;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getAmount() {
        return amount;
    }

    public Timestamp getOrderTimes() {
        return orderTimes;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
