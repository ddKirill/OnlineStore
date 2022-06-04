package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllProductsWithFullInformation {

    private ProductRepository productRepository;

    public GetAllProductsWithFullInformation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<AllProductsWithFullInformation> getAllProducts() {

        Iterable<ProductEntity> productEntity = productRepository.findAll();
        ArrayList<AllProductsWithFullInformation> allProductsWithFullInformationList = new ArrayList<>();

        for (ProductEntity product : productEntity) {

            AllProductsWithFullInformation allProductsWithFullInformation = new AllProductsWithFullInformation(
                    product.getId(), product.getTitle(), product.getPrice(), product.getDescription(), product.getLocationImage());

            allProductsWithFullInformationList.add(allProductsWithFullInformation);
        }
        return allProductsWithFullInformationList;
    }
}
