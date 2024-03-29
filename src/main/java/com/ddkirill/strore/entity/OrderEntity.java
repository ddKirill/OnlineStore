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
    private Integer orderPrice;

    @MappedCollection(idColumn = "order_number")
    private Set<ProductInOrder> productsInOrder = new HashSet<>();

    public OrderEntity(Long orderNumber, String status, Timestamp orderRegistered, Integer orderPrice, Set<ProductInOrder> productsInOrder) {
        this.orderNumber = orderNumber;
        this.status = status;
        this.orderRegistered = orderRegistered;
        this.orderPrice = orderPrice;
        this.productsInOrder = productsInOrder;
    }

    public OrderEntity() {

    }

    public void addProductsInOrder(ProductEntity product, int productAmount) {
        productsInOrder.add(new ProductInOrder(product.getProductId(), productAmount));
    }

    public Long getOrderNumber() {
        return orderNumber;
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

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Set<ProductInOrder> getProductsInOrder() {
        return productsInOrder;
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
