package com.demo.repository;

import com.demo.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.net.URLConnection;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {


}
