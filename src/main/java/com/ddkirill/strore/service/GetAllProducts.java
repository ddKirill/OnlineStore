package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetAllProducts {

    private ProductRepository productRepository;

    public GetAllProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<AllProducts> getAllProducts(){
        Iterable<ProductEntity> productEntity = productRepository.findAll();
        List<AllProducts>  allProductsList = new ArrayList<>();

        for (ProductEntity product : productEntity) {
            AllProducts allProducts = new AllProducts(product.getTitle(),
                    product.getPrice());
            allProductsList.add(allProducts);
        }
        return allProductsList;

    }
}
