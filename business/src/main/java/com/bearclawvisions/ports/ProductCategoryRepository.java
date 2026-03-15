package com.bearclawvisions.ports;

import com.bearclawvisions.entitities.ProductCategory;

import java.util.Optional;

public interface ProductCategoryRepository {
    Optional<ProductCategory> findById(int id);
    Optional<ProductCategory> findByName(String name);
    Optional<ProductCategory> findByDescription(String description);
    void save(ProductCategory productCategory);
}