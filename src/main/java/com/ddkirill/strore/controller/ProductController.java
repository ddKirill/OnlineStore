package com.ddkirill.strore.controller;

import com.ddkirill.strore.controller.dto.ProductsDTO;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.domain.Product;
import com.ddkirill.strore.service.products.GetAllProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final GetAllProducts getAllProducts;

    @Autowired
    public ProductController(ProductRepository productRepository, GetAllProducts getAllProducts) {
        this.productRepository = productRepository;
        this.getAllProducts = getAllProducts;
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model) {

        List<Product> allProducts = getAllProducts.getAllProducts();
        List<ProductsDTO> productsDTOS = new ArrayList<>();

        for (Product allProduct : allProducts) {
            ProductsDTO productsDTO = new ProductsDTO(allProduct.getTitle(),allProduct.getPrice(),
                    allProduct.getDescription(), allProduct.getLocationImage());
            productsDTOS.add(productsDTO);
        }

        model.addAttribute("productList", productsDTOS);
        return "allProducts";

    }

}
