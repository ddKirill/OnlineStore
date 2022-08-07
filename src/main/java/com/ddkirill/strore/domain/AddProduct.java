package com.ddkirill.strore.domain;

public class AddProduct {

    private String title;
    private int price;
    private String description;
    private String locationImage;

    public AddProduct(String title, int price, String description, String locationImage) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.locationImage = locationImage;
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

    public String getLocationImage() {
        return locationImage;
    }
}
