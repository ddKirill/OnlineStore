package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.*;
import com.ddkirill.strore.enums.OrderStatusEnum;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.repository.UserRepository;
import com.ddkirill.strore.service.products.ProductHandlerService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OrderHandlerService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductHandlerService productHandlerService;


    public OrderHandlerService(OrderRepository orderRepository, UserRepository userRepository, ProductHandlerService productHandlerService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productHandlerService = productHandlerService;
    }

    public OrderEntity createOrder(){
        OrderEntity firstOrder = new OrderEntity();

        firstOrder.setStatus(OrderStatusEnum.AWAITING_PAYMENT.getStatus());
        System.out.println("Корзина создана");
        orderRepository.save(firstOrder);
        return firstOrder;
    }

    public OrderEntity getOrderByOrderNumber(long orderNumber){
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(orderNumber);
        OrderEntity order = new OrderEntity();
        if (optionalOrderEntity.isPresent()) {
            order = optionalOrderEntity.get();
        }
        return order;
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

    public String viewCurrentOrder(OrderEntity currentOrder) {
        var orderNumber = currentOrder.getOrderNumber().toString();
        //var orderPrice = currentOrder.getOrderPrice().toString();
        StringBuilder stringBuilder = new StringBuilder();

        Set<ProductInOrder> productInOrderSet = currentOrder.getProductsInOrder();
        for (ProductInOrder productInOrder : productInOrderSet) {
            var productId = productInOrder.getProductId();
            var productAmount = productInOrder.getProductAmount();
            ProductEntity product = productHandlerService.getProductById(productId);
            String title = product.getTitle();
            int price = product.getPrice();
            stringBuilder.append(title + "\n");
            stringBuilder.append(price + "₽" + "\n");
            stringBuilder.append("Количество продукта " + productAmount + "\n");
        }
        return String.join("\n", "Номер заказа " + orderNumber, stringBuilder);
    }

    private Optional<OrderEntity> getIterableOrderEntity(long orderNumber) {
        return orderRepository.findById(orderNumber);
    }

}
