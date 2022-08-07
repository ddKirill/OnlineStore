package com.ddkirill.strore.controller.dto;

import java.util.UUID;

public class AllProductsWithFullInformationDTO {

    private UUID id;
    private String title;
    private int price;
    private String description;
    private String locationImage;

    public AllProductsWithFullInformationDTO(UUID id, String title, int price, String description, String locationImage) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.locationImage = locationImage;
    }

    public UUID getId() {
        return id;
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
