package com.bearclawvisions.ports;

import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    Product findById(int id);
    Product findByCategoryId(int categoryId);
    Product findByName(String name);
    Product findByPrice(BigDecimal price);
    List<Product> findAll();

    List<Product> findAllByStatus(ProductStatus status);
    List<Product> findAllByCategoryId(int categoryId);
    List<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> findAllByPriceGreaterThanEqual(BigDecimal minPrice);
    List<Product> findAllByPriceLessThanEqual(BigDecimal maxPrice);
    List<Product> findAllByExpiryDateGreaterThanEqual(LocalDateTime expiryDate);

    // this should use a dto
    void insertProduct(
            Integer categoryId,
            String name,
            String metadataXml,
            BigDecimal price,
            Integer quantity,
            ProductStatus status,
            LocalDateTime expiryDate,
            String createdBy,
            LocalDateTime createdOn
    );

    Product save(Product product);
}
