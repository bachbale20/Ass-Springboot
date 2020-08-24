package com.demo.controller;

import com.demo.entity.Product;
import com.demo.repository.ProductRepo;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
@Controller

public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepo productRepository;

    @GetMapping("/list")
    public String getAllProduct (Model model){
        model.addAttribute("productList", productService.getAllProduct());
        return "listproduct";
    }

    @GetMapping("/addProduct")
    public String showAddProduct(Model model){
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct (@ModelAttribute Product product){
        return Optional.ofNullable(productService.createProduct(product))
                .map(t -> "list")
                .orElse("failed");
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("product", product);
        return "update-product";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
    public String updateProduct(@PathVariable("id") Integer id, Product product, Model model, BindingResult result) {

        if (result.hasErrors()){
            product.setId(id);
            return "updateproduct";
        }

        productService.updateProduct(product);
        model.addAttribute("product", productRepository.findAll());
        return "redirect:/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productRepository.delete(product);
        model.addAttribute("product", productRepository.findAll());
        return "list";
    }
}