package com.ddkirill.strore.controller;


import com.ddkirill.strore.entity.AddProduct;
import com.ddkirill.strore.entity.ProductEntity;
import com.ddkirill.strore.repository.ProductRepository;
import com.ddkirill.strore.service.GetAllProductsWithFullInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        getAllProductsWithFullInformation.getAllProducts(productRepository);
        model.addAttribute(getAllProductsWithFullInformation);

        return "manageProducts";
    }

    @PostMapping("/allProducts/manageProducts")
    public String addProduct(@ModelAttribute AddProduct addProduct) {

        ProductEntity product = new ProductEntity(null, addProduct.getTitle(), addProduct.getPrice(),
                addProduct.getDescription(), addProduct.getLocationImage());
        productRepository.save(product);

        return "redirect:manageProducts";

    }


}
