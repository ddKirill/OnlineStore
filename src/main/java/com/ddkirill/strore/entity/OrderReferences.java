package com.ddkirill.strore.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table(value = "users_orders")
public class OrderReferences {

    Long orderNumber;

    public OrderReferences(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
