package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class SelectedProductService {
//
//    private ProductRepository productRepository;
//    private ProductEntity productEntity;
//
//
//    public SelectedProductService(ProductRepository productRepository, ProductEntity productEntity) {
//        this.productRepository = productRepository;
//        this.productEntity = productEntity;
//    }
//
//    public SelectedProduct getSelectedProduct() {
//        Iterable<ProductEntity> productEntities = productRepository.findById();
//        SelectedProduct selectedProductList;
//
//        for (ProductEntity productEntity : productEntities) {
//            SelectedProduct selectedProduct = new SelectedProduct(productEntity.getTitle(), productEntity.getPrice(),
//                    productEntity.getDescription(), productEntity.getCategory());
//            selectedProduct.(selectedProduct);
//        }
//
//        return selectedProductList;
//
//    }
//}
