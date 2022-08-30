package com.ddkirill.strore.controller.dto;

import com.ddkirill.strore.domain.Product;

import java.util.UUID;

public class AllProductsWithFullInformationDTO extends Product {

    public AllProductsWithFullInformationDTO(Long id, String title, Integer price, String description, String locationImage) {
        super(id, title, price, description, locationImage);
    }
}
