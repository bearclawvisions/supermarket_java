package com.bearclawvisions.repositories;

import com.bearclawvisions.entitities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    Optional<ProductCategory> findById(int id);
    Optional<ProductCategory> findByName(String name);
    Optional<ProductCategory> findByDescription(String description);
}
