package com.bearclawvisions.repositories;

import com.bearclawvisions.entitities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    ProductCategory findById(int id);
    ProductCategory findByName(String name);
    ProductCategory findByDescription(String description);
}
