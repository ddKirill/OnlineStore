package com.ddkirill.strore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Table(value = "orders")
public class OrderEntity {

    @Id
    private Long orderNumber;
    private String status;
    private Timestamp orderRegistered;
    private Long orderPrice;

    @MappedCollection(idColumn = "order_number")
    private Set<ProductInOrder> productsInOrder = new HashSet<>();

    public OrderEntity(Long orderNumber, String status, Timestamp orderRegistered, Long orderPrice, Set<ProductInOrder> productsInOrder) {
        this.orderNumber = orderNumber;
        this.status = status;
        this.orderRegistered = orderRegistered;
        this.orderPrice = orderPrice;
        this.productsInOrder = productsInOrder;
    }

    public OrderEntity() {
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
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

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Set<ProductInOrder> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(Set<ProductInOrder> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderNumber=" + orderNumber +
                ", status='" + status + '\'' +
                ", orderRegistered=" + orderRegistered +
                ", orderPrice=" + orderPrice +
                ", productsInOrder=" + productsInOrder +
                '}';
    }
}
