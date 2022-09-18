package com.ddkirill.strore.service.products;


import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.model.Product;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductHandlerService {

    private final ProductRepository productRepository;
    private OrderRepository orderRepository;

    public ProductHandlerService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public void deleteByTitle(String title){
        productRepository.deleteByTitle(title);
    }

    public List<Product> getAllProducts(){
        Iterable<ProductEntity> iterableProductEntity = getIterableProductEntity();
        List<Product> allProductsList = new ArrayList<>();

        for (ProductEntity product : iterableProductEntity) {
            Product productIter = new Product(product.getProductId(), product.getTitle(),
                    product.getPrice(), product.getDescription(), product.getLocationImage());

            allProductsList.add(productIter);
        }
        return allProductsList;
    }

    public ProductEntity getProductById(long productId) {
        Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
        ProductEntity product = null;
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            System.out.println("Продукт не найден.");
        }
        return product;
    }

    public List<String> getAllProductIdList() {
        Iterable<ProductEntity> iterableProductEntity = getIterableProductEntity();
        List<String> productIdList = new ArrayList<>();
        for (ProductEntity allProduct : iterableProductEntity) {
            String productIdToString = allProduct.getProductId().toString();
            productIdList.add(productIdToString);
        }
        return productIdList;
    }

    private Iterable<ProductEntity> getIterableProductEntity(){
        return productRepository.findAll();
    }

}
