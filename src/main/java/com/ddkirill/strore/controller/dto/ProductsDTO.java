package com.ddkirill.strore.controller.dto;

import com.ddkirill.strore.domain.Product;

public class ProductsDTO extends Product {

    public ProductsDTO(Long id, String title, Integer price, String description, String locationImage) {
        super(id, title, price, description, locationImage);
    }
}
