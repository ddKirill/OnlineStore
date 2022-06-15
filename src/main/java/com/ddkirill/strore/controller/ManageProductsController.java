package com.ddkirill.strore.controller;


import com.ddkirill.strore.controller.dto.AllProductsWithFullInformationDTO;
import com.ddkirill.strore.domain.AddProduct;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.domain.AllProductsWithFullInformation;
import com.ddkirill.strore.service.products.GetAllProductsWithFullInformation;
import com.ddkirill.strore.service.products.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageProductsController {

    private ProductRepository productRepository;
    private GetAllProductsWithFullInformation getAllProductsWithFullInformation;
    private ProductService productService;

    public ManageProductsController(ProductRepository productRepository,
                                    GetAllProductsWithFullInformation getAllProductsWithFullInformation, ProductService productService) {
        this.productRepository = productRepository;
        this.getAllProductsWithFullInformation = getAllProductsWithFullInformation;
        this.productService = productService;
    }

    @GetMapping("/allProducts/manageProducts")
    public String allProducts(Model model){

        List<AllProductsWithFullInformation> allProductsWithFullInformation = getAllProductsWithFullInformation.getAllProducts();
        List<AllProductsWithFullInformationDTO> allProductsWithFullInformationDTOS = new ArrayList<>();

        for (AllProductsWithFullInformation products : allProductsWithFullInformation) {

            AllProductsWithFullInformationDTO allProducts = new AllProductsWithFullInformationDTO(null, products.getTitle(),
                    products.getPrice(), products.getDescription(), products.getLocationImage());

            allProductsWithFullInformationDTOS.add(allProducts);
        }
        model.addAttribute("allProducts",allProductsWithFullInformationDTOS);

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
        productService.deleteByTitle(title);
        return "redirect:allProducts/manageProducts";
    }




}
