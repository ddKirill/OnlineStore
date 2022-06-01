package com.ddkirill.strore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table (value = "product")
public class ProductEntity {

    @Id
    private UUID id;
    private String title;
    private int price;
    private String description;
    private String locationImage;

    public ProductEntity(UUID id, String title, int price, String description, String locationImage) {
        this.id = id;
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

    public void setTitle(String title) {
        this.title = title;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(String locationImage) {
        this.locationImage = locationImage;
    }
}
