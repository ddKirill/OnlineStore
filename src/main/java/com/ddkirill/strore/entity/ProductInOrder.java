package com.ddkirill.strore.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table(value = "product_in_order")
public class ProductInOrder {

    private Long productId;
    private int productAmount;

    public ProductInOrder(Long productId, int productAmount) {
        this.productId = productId;
        this.productAmount = productAmount;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
}
