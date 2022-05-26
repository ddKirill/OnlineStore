package com.ddkirill.strore.controller;

import com.ddkirill.strore.controller.dto.ProductsDTO;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.service.AllProducts;
import com.ddkirill.strore.service.GetAllProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    private ProductRepository productRepository;
    private GetAllProducts getAllProducts;

    @Autowired
    public ProductController(ProductRepository productRepository, GetAllProducts getAllProducts) {
        this.productRepository = productRepository;
        this.getAllProducts = getAllProducts;
    }

    @GetMapping("/allProducts")
    public String allProducts(Model model) {

        List<AllProducts> allProducts = getAllProducts.getAllProducts();
        List<ProductsDTO> productsDTOS = new ArrayList<>();

        for (AllProducts allProduct : allProducts) {
            ProductsDTO productsDTO = new ProductsDTO(allProduct.getTitle(),allProduct.getPrice());
            productsDTOS.add(productsDTO);
        }

        model.addAttribute("productList", productsDTOS);
        return "allProducts";

    }



    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }
}
