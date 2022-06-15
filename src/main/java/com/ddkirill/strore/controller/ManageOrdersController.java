package com.ddkirill.strore.controller;

import com.ddkirill.strore.controller.dto.AllOrdersDTO;
import com.ddkirill.strore.domain.AllOrders;
import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.repository.OrderRepository;
import com.ddkirill.strore.service.orders.ManageOrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ManageOrdersController {

    private ManageOrdersService ordersService;
    private OrderRepository orderRepository;

    public ManageOrdersController(ManageOrdersService ordersService, OrderRepository orderRepository) {
        this.ordersService = ordersService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/manageOrders")
    public String allOrders(Model model) {
        Iterable<OrderEntity> orderEntity = orderRepository.findAll();
        List<AllOrdersDTO> allOrdersDTOS = new ArrayList<>();

        for (OrderEntity entity : orderEntity) {
            AllOrdersDTO allOrdersDTO = new AllOrdersDTO(entity.getNumber(), entity.getBuyer(),entity.getProductTitle(),
                    entity.getAmount(),entity.getOrderTimes(),entity.getTotalCost());
            allOrdersDTOS.add(allOrdersDTO);
        }
        model.addAttribute("orderList", allOrdersDTOS);

        return "manageOrders";
    }
}
