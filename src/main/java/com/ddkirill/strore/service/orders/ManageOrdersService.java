package com.ddkirill.strore.service.orders;

import com.ddkirill.strore.domain.AllOrders;
import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageOrdersService {

    private OrderRepository orderRepository;

    public ManageOrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<AllOrders> getAllOrders(OrderRepository orderRepository) {

        Iterable<OrderEntity> allOrders = orderRepository.findAll();
        List<AllOrders> allOrdersList = new ArrayList<>();

        for (OrderEntity allOrder : allOrders) {
            AllOrders order = new AllOrders(allOrder.getNumber(),allOrder.getBuyer(),
                    allOrder.getProductTitle(),allOrder.getAmount(),allOrder.getOrderTimes(),allOrder.getTotalCost());
            allOrdersList.add(order);
        }
        return allOrdersList;

    }
}
