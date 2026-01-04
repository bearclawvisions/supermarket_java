package com.bearclawvisions.repositories;

import com.bearclawvisions.entitities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);
    Product findByCategoryId(int categoryId);
    Product findByName(String name);
    Product findByPrice(BigDecimal price);
}
