package com.ddkirill.strore.controller;

import com.ddkirill.strore.controller.dto.ProductsDTO;
import com.ddkirill.strore.model.Product;
import com.ddkirill.strore.service.products.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    private final ProductManageService productManageService;

    @Autowired
    public ProductController(ProductManageService productManageService) {
        this.productManageService = productManageService;
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model) {

        List<Product> allProducts = productManageService.getAllProducts();
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
