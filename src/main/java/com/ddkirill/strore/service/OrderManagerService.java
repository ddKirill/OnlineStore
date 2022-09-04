package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.OrderReferences;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.entity.UserEntity;
import com.ddkirill.strore.enums.OrderStatusEnum;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OrderManagerService {

    private final OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private UserManagerService userManagerService;


    public OrderManagerService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository, UserManagerService userManagerService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userManagerService = userManagerService;
    }

    //Method for creating first order
    public OrderEntity createOrder(){
        OrderEntity firstOrder = new OrderEntity();
        firstOrder.setStatus(OrderStatusEnum.AWAITING_PAYMENT.getStatus());

        System.out.println("Корзина создана");
        orderRepository.save(firstOrder);
        return firstOrder;
    }

    //Add 1 product
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

    public OrderEntity getCurrentOrder(Long chatId) {
        UserEntity user = userManagerService.getUserById(chatId);
        Set<OrderReferences> orderReferences = user.getOrderReferences();
        OrderReferences orderReference = orderReferences.iterator().next();
        Long orderNumber = orderReference.getOrderNumber();
        Optional<OrderEntity> optionalOrder = orderRepository.findById(orderNumber);
        OrderEntity order = null;
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
        }
        return order;
    }


}
