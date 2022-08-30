package com.ddkirill.strore.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table(value = "product_in_order")
public class ProductInOrder {

    private final Long id;
    private final int productAmount;

    public ProductInOrder(Long id, int productAmount) {
        this.id = id;
        this.productAmount = productAmount;
    }


    public int getProductAmount() {
        return productAmount;
    }


    public Long getId() {
        return id;
    }
}
