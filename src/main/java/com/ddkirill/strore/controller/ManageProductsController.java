package com.ddkirill.strore.controller;


import com.ddkirill.strore.controller.dto.AllProductsWithFullInformationDTO;
import com.ddkirill.strore.entity.AddProduct;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.service.AllProductsWithFullInformation;
import com.ddkirill.strore.service.GetAllProductsWithFullInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ManageProductsController {

    private ProductRepository productRepository;
    private GetAllProductsWithFullInformation getAllProductsWithFullInformation;

    public ManageProductsController(ProductRepository productRepository,
                                    GetAllProductsWithFullInformation getAllProductsWithFullInformation) {
        this.productRepository = productRepository;
        this.getAllProductsWithFullInformation = getAllProductsWithFullInformation;
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

    @PostMapping(value = "/deleteProduct/{id}")
    public String deleteProduct(@RequestParam (name = "id") UUID uuid) {
        productRepository.deleteById(uuid);
        return "redirect:manageProducts";
    }




}
