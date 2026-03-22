package com.bearclawvisions.dto.product;

import com.bearclawvisions.dto.models.ProductMetadata;
import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ProductStatus;
import com.bearclawvisions.utils.XmlUtils;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.JAXBException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDto {

    private Integer id;

    @NotNull
    private Integer categoryId;

    @NotBlank
    private String name;

    @NotNull
    private ProductMetadata metadataXml;

    @NotNull
    @DecimalMin(value = "0", inclusive = true)
    @DecimalMax(value = "10000", inclusive = true)
    private BigDecimal price;

    @NotBlank
    private Integer quantity;

    @NotNull
    private ProductStatus status;

    private LocalDateTime expiryDate;

    public ProductDto() {}

    public ProductDto(Integer id, Integer categoryId, String name, ProductMetadata metadataXml,
                      BigDecimal price, Integer quantity, ProductStatus status, LocalDateTime expiryDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.metadataXml = metadataXml;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.expiryDate = expiryDate;
    }

    public static ProductDto toDto(Product product) {

        ProductMetadata productMetadata;
        try {
            productMetadata = XmlUtils.deserializeToJava(product.getMetadataXml(), ProductMetadata.class);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to deserialize product metadata", e);
        }

        return new ProductDto(
                product.getId(),
                product.getCategoryId(),
                product.getName(),
                productMetadata,
                product.getPrice(),
                product.getQuantity(),
                product.getStatus(),
                product.getExpiryDate()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductMetadata getMetadataXml() {
        return metadataXml;
    }

    public void setMetadataXml(ProductMetadata metadataXml) {
        this.metadataXml = metadataXml;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
