package com.ddkirill.strore.service.products;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.ProductInOrder;
import com.ddkirill.strore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductInOrderService {

    private final OrderRepository orderRepository;

    public ProductInOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public boolean addProductInOrder(OrderEntity order, Long productId) {

        ProductInOrder newProduct = new ProductInOrder();
        Set<ProductInOrder> productInOrderSet = order.getProductsInOrder();
        for (ProductInOrder productInOrder : productInOrderSet) {

            if (productInOrder.getProductId().equals(productId)) {
                newProduct.setProductId(productInOrder.getProductId());
                newProduct.setProductAmount(productInOrder.getProductAmount());
            }
        }

        if (productInOrderSet.contains(newProduct)) {
            productInOrderSet.remove(newProduct);
            productInOrderSet.add(new ProductInOrder(newProduct.getProductId(), newProduct.getProductAmount() + 1));
        } else {
            productInOrderSet.add(new ProductInOrder(productId, 1));
        }

        orderRepository.save(order);
        System.out.println("Товар добавлен в корзину!");
        return true;
    }

}
