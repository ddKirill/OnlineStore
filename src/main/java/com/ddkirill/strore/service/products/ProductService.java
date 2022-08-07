package com.ddkirill.strore.service.products;


import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity save(ProductEntity product){
        return productRepository.save(product);
    }

    public void deleteByTitle(String title){
        productRepository.deleteByTitle(title);
    }
}
