package com.ddkirill.strore.service.products;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductInOrderManager {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public ProductInOrderManager(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public boolean addProductInOrder(Long orderNumber, Long productId) {

        Optional<OrderEntity> orderEntity = orderRepository.findById(orderNumber);
        Optional<ProductEntity> productEntity = productRepository.findById(productId);

        if (orderEntity.isPresent() && productEntity.isPresent()) {
            OrderEntity order = orderEntity.get();
            ProductEntity product = productEntity.get();
            order.addProductsInOrder(product, 1);
            orderRepository.save(order);
            System.out.println("Товар добавлен в корзину!");
            return true;
        } else {
            System.out.println("Товар или заказ не найден!!!");
            return false;
        }
    }
}
