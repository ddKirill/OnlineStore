package com.ddkirill.strore.controller;

import com.ddkirill.strore.controller.dto.ProductsDTO;
import com.ddkirill.strore.model.Product;
import com.ddkirill.strore.service.products.ProductHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    private final ProductHandlerService productHandlerService;

    @Autowired
    public ProductController(ProductHandlerService productHandlerService) {
        this.productHandlerService = productHandlerService;
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model) {

        List<Product> allProducts = productHandlerService.getAllProducts();
        List<ProductsDTO> productsDTOS = new ArrayList<>();

        for (Product allProduct : allProducts) {
            ProductsDTO productsDTO = new ProductsDTO(null, allProduct.getTitle(),allProduct.getPrice(),
                    allProduct.getDescription(), allProduct.getLocationImage());
            productsDTOS.add(productsDTO);
        }

        model.addAttribute("productList", productsDTOS);
        return "allProducts";

    }

}
