package com.ddkirill.strore.service.products;

import com.ddkirill.strore.domain.Product;
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

    public List<Product> getAllProducts() {

        Iterable<ProductEntity> productEntity = productRepository.findAll();
        List<Product> allProductsWithFullInformationList = new ArrayList<>();

        for (ProductEntity product : productEntity) {

            Product allProductsWithFullInformation = new Product(
                    product.getId(), product.getTitle(), product.getPrice(), product.getDescription(), product.getLocationImage());

            allProductsWithFullInformationList.add(allProductsWithFullInformation);
        }
        return allProductsWithFullInformationList;
    }
}
