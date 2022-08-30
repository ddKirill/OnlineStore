package com.ddkirill.strore.domain;


public class Product {

    private Long id;
    private final String title;
    private final Integer price;
    private final String description;
    private final String locationImage;

    public Product(String title, Integer price, String description, String locationImage) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.locationImage = locationImage;
    }

    public Product(Long id, String title, Integer price, String description, String locationImage) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.locationImage = locationImage;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationImage() {
        return locationImage;
    }
}
