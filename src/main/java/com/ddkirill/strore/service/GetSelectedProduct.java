package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSelectedProduct {

    private ProductRepository productRepository;

    public GetSelectedProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<SelectedProduct> getSelectedProduct() {
        Iterable<ProductEntity> productEntities = productRepository.findAll();


    }
}
