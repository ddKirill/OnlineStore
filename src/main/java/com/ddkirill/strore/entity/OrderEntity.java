package com.ddkirill.strore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Table(value = "orders")
public class OrderEntity {

    @Id
    private Integer orderNumber;
    private Long buyer;
    @Column(value = "products")
    private List<UUID> products;
    private Integer amount;
    @Column(value = "status")
    private String status;
    private Timestamp orderRegistered;
    private Integer orderPrice;

    public OrderEntity(Integer orderNumber, Long buyer, List<UUID> products, Integer amount,
                       String status, Timestamp orderRegistered, Integer orderPrice) {
        this.orderNumber = orderNumber;
        this.buyer = buyer;
        this.products = products;
        this.amount = amount;
        this.status = status;
        this.orderRegistered = orderRegistered;
        this.orderPrice = orderPrice;
    }

    public Long getBuyer() {
        return buyer;
    }

    public void setBuyer(Long buyer) {
        this.buyer = buyer;
    }

    public List<UUID> getProducts() {
        return products;
    }

    public void setProducts(List<UUID> products) {
        this.products = products;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderRegistered() {
        return orderRegistered;
    }

    public void setOrderRegistered(Timestamp orderRegistered) {
        this.orderRegistered = orderRegistered;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
}
