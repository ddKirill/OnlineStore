package com.ddkirill.strore.controller;


import com.ddkirill.strore.controller.dto.ProductsDTO;
import com.ddkirill.strore.model.AddProduct;
import com.ddkirill.strore.model.Product;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.service.products.ProductManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageProductsController {

    private final ProductManageService productManageService;
    private final ProductRepository productRepository;

    public ManageProductsController(ProductManageService productManageService, ProductRepository productRepository) {
        this.productManageService = productManageService;
        this.productRepository = productRepository;
    }

    @GetMapping("/allProducts/manageProducts")
    public String allProducts(Model model){

        List<Product> allProducts = productManageService.getAllProducts();
        List<ProductsDTO> allProductsDTO = new ArrayList<>();

        for (Product products : allProducts) {

            ProductsDTO productDTO = new ProductsDTO(null, products.getTitle(),
                    products.getPrice(), products.getDescription(), products.getLocationImage());

            allProductsDTO.add(productDTO);
        }
        model.addAttribute("allProducts",allProductsDTO);

        return "manageProducts";
    }

    @PostMapping("/allProducts/manageProducts")
    public String addProduct(@ModelAttribute AddProduct addProduct) {

        ProductEntity product = new ProductEntity(null, addProduct.getTitle(), addProduct.getPrice(),
                addProduct.getDescription(), addProduct.getLocationImage());
        productRepository.save(product);

        return "redirect:manageProducts";

    }

    @GetMapping( "/deleteProduct")
    public String deleteProduct(@RequestParam String title) {
        productManageService.deleteByTitle(title);
        return "redirect:allProducts/manageProducts";
    }




}
