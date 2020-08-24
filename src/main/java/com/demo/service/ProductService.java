package com.demo.service;

import com.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();
    Product createProduct(Product product);
    Product updateProduct(Product product);

}
