package com.bearclawvisions.adapters;

import com.bearclawvisions.entitities.ProductCategory;
import com.bearclawvisions.ports.ProductCategoryRepository;
import com.bearclawvisions.repositories.JpaProductCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductCategoryRepositoryAdapter implements ProductCategoryRepository {

    private final JpaProductCategoryRepository jpaProductCategoryRepository;

    public ProductCategoryRepositoryAdapter(JpaProductCategoryRepository jpaProductCategoryRepository) {
        this.jpaProductCategoryRepository = jpaProductCategoryRepository;
    }

    @Override
    public Optional<ProductCategory> findById(int id) {
        return jpaProductCategoryRepository.findById(id);
    }

    @Override
    public Optional<ProductCategory> findByName(String name) {
        return jpaProductCategoryRepository.findByName(name);
    }

    @Override
    public Optional<ProductCategory> findByDescription(String description) {
        return jpaProductCategoryRepository.findByDescription(description);
    }

    @Override
    public void save(ProductCategory productCategory) {
        jpaProductCategoryRepository.save(productCategory);
    }
}