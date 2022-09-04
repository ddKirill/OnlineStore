package com.ddkirill.strore.service.products;


import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.model.Product;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManageService {

    private final ProductRepository productRepository;

    public ProductManageService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteByTitle(String title){
        productRepository.deleteByTitle(title);
    }

    public List<Product> getAllProducts(){

        Iterable<ProductEntity> productEntity = productRepository.findAll();
        List<Product> productList = new ArrayList<>();

        for (ProductEntity product : productEntity) {
            Product allProducts = new Product(product.getProductId(), product.getTitle(),
                    product.getPrice(), product.getDescription(), product.getLocationImage());

            productList.add(allProducts);
        }
        return productList;
    }
}
