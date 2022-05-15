package com.ddkirill.strore.service;

public class SelectedProduct {

    private final String title;
    private final int price;
    private final String description;
    private final String category;

    public SelectedProduct(String title, int price, String description, String category) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
