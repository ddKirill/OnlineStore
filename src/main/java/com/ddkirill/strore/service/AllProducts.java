package com.ddkirill.strore.service;

public class AllProducts {

    private final String title;
    private final Integer price;

    public AllProducts(String title, Integer price) {
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
