package com.ddkirill.strore.controller.dto;

public class ProductsDTO {

    private final String title;
    private final Integer price;

    public ProductsDTO(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }
}
