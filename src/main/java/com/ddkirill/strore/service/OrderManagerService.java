package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.OrderReferences;
import com.ddkirill.strore.entity.UserEntity;
import com.ddkirill.strore.enums.OrderStatusEnum;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OrderManagerService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;


    public OrderManagerService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public OrderEntity createOrder(){
        OrderEntity firstOrder = new OrderEntity();

        firstOrder.setStatus(OrderStatusEnum.AWAITING_PAYMENT.getStatus());
        System.out.println("Корзина создана");
        orderRepository.save(firstOrder);
        return firstOrder;
    }

    public OrderEntity getOrderByOrderNumber(long orderNumber){
        Optional<OrderEntity> optionalOrderEntity = getIterableOrderEntity(orderNumber);
        OrderEntity orderEntity;
        if (optionalOrderEntity.isPresent()) {
            orderEntity = optionalOrderEntity.get();
            return orderEntity;
        } else {
            return null;
        }
    }

    public OrderEntity getCurrentOrder(Long chatId) {
        Optional<UserEntity> optionalUser = userRepository.findById(chatId);
        UserEntity user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            System.out.println("Пользователь не найден или Null");
            return null;
        }
        Set<OrderReferences> orderReferences = user.getOrderReferences();
        OrderReferences orderReference = orderReferences.iterator().next();
        Long orderNumber = orderReference.getOrderNumber();
        Optional<OrderEntity> optionalOrder = getIterableOrderEntity(orderNumber);
        OrderEntity order = null;
        if (optionalOrder.isPresent()) {
            order = optionalOrder.get();
        }
        return order;
    }

    private Optional<OrderEntity> getIterableOrderEntity(long orderNumber) {
        return orderRepository.findById(orderNumber);
    }

}
