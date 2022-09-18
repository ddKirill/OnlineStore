package com.ddkirill.strore.entity;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table(value = "product_in_order")
public class ProductInOrder {

    private Long productId;
    private int productAmount;

    public ProductInOrder(Long productId, int productAmount) {
        this.productId = productId;
        this.productAmount = productAmount;
    }

    public ProductInOrder() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrder that = (ProductInOrder) o;
        return productAmount == that.productAmount && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productAmount);
    }
}
