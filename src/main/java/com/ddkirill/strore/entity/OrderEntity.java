package com.ddkirill.strore.entity;

import org.glassfish.grizzly.http.util.TimeStamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Table (value = "orders")
public class OrderEntity {

    @Id
    private UUID id;
    private int number;
    private String buyer;
    private String productTitle;
    private int amount;
    private Timestamp orderTimes;
    private int totalCost;

    public OrderEntity(UUID id, int number, String buyer, String productTitle, int amount,
                       Timestamp timestamp, int totalCost) {
        this.id = id;
        this.number = number;
        this.buyer = buyer;
        this.productTitle = productTitle;
        this.amount = amount;
        this.orderTimes = timestamp;
        this.totalCost = totalCost;
    }

    public UUID getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(Timestamp orderTimes) {
        this.orderTimes = orderTimes;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
