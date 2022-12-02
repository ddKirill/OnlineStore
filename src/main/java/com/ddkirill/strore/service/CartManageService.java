package com.ddkirill.strore.service;

import com.ddkirill.strore.entity.OrderEntity;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.entity.ProductInOrder;
import com.ddkirill.strore.service.products.ProductHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CartManageService {

    private final ProductHandlerService productHandlerService;

    @Autowired
    public CartManageService(ProductHandlerService productHandlerService) {
        this.productHandlerService = productHandlerService;
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
}
