package com.ddkirill.strore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table (value = "order")
public class OrderEntity {

    @Id
    private UUID id;
    private UUID buyer;
    private UUID item;
    private int amount;
    private String status;

    public OrderEntity(UUID id, UUID buyer, UUID item, int amount, String status) {
        this.id = id;
        this.buyer = buyer;
        this.item = item;
        this.amount = amount;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBuyer() {
        return buyer;
    }

    public void setBuyer(UUID buyer) {
        this.buyer = buyer;
    }

    public UUID getItem() {
        return item;
    }

    public void setItem(UUID item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
