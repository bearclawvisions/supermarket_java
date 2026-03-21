package com.bearclawvisions.adapters;

import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ProductStatus;
import com.bearclawvisions.ports.ProductRepository;
import com.bearclawvisions.repositories.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product findById(int id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public Product findByCategoryId(int categoryId) {
        return jpaProductRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product findByName(String name) {
        return jpaProductRepository.findByName(name);
    }

    @Override
    public Product findByPrice(BigDecimal price) {
        return jpaProductRepository.findByPrice(price);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll();
    }

    @Override
    public List<Product> findAllByStatus(ProductStatus status) {
        return jpaProductRepository.findAllByStatus(status);
    }

    @Override
    public List<Product> findAllByCategoryId(int categoryId) {
        return jpaProductRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal minPrice, BigDecimal maxPrice) {
        return jpaProductRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAllByPriceGreaterThanEqual(BigDecimal minPrice) {
        return jpaProductRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    @Override
    public List<Product> findAllByPriceLessThanEqual(BigDecimal maxPrice) {
        return jpaProductRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    @Override
    public List<Product> findAllByExpiryDateGreaterThanEqual(LocalDateTime expiryDate) {
        return jpaProductRepository.findAllByExpiryDateGreaterThanEqual(expiryDate);
    }

    @Override
    public void insertProduct(Integer categoryId, String name, String metadataXml, BigDecimal price, Integer quantity, ProductStatus status, LocalDateTime expiryDate, String createdBy, LocalDateTime createdOn) {
        jpaProductRepository.insertProduct(categoryId, name, metadataXml, price, quantity, status, expiryDate, createdBy, createdOn);
    }

    @Override
    public Product save(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        jpaProductRepository.deleteById(id);
    }
}
