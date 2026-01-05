package com.bearclawvisions.repositories;

import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);
    Product findByCategoryId(int categoryId);
    Product findByName(String name);
    Product findByPrice(BigDecimal price);

    @Modifying // tell spring that this query modifies the database Insert, Update, Delete statements
    @Transactional // ensures proper transaction management
    @Query(value = """
        INSERT INTO public.products (
                    category_id,
                    name,
                    metadata_xml,
                    price,
                    quantity,
                    status,
                    expiry_date,
                    created_by,
                    created_on
                    )
        VALUES (
                :categoryId,
                :name,
                CAST(:metadataXml AS xml),
                :price,
                :quantity,
                :status,
                :expiryDate,
                :createdBy,
                :createdOn
                )
        """, nativeQuery = true)
    void insertProduct(
            @Param("categoryId") Integer categoryId,
            @Param("name") String name,
            @Param("metadataXml") String metadataXml,
            @Param("price") BigDecimal price,
            @Param("quantity") Integer quantity,
            @Param("status") ProductStatus status,
            @Param("expiryDate") LocalDateTime expiryDate,
            @Param("createdBy") String createdBy,
            @Param("createdOn") LocalDateTime createdOn
    );
}
