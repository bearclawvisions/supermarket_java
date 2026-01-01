package com.bearclawvisions.entitities;

import com.bearclawvisions.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // the below is possible, but does not enforce foreign key constraint
    // has to be done in postgresql, but might obfuscate logic in java
    // does make the logic simpler and faster, but no lazy loading
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    // this also tells JPA the foreign key relationship
    // loading/join is done via calling .getCategory() on the product
    // needs caution to prevent N+1 query problem
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
//    private ProductCategory category;

    //@NotBlank(message = "Product name cannot be blank") // used in conjunction with the @Valid annotation in endpoints, throws ConstraintViolationException
    //@Size(max = 50, message = "Product name must not exceed 50 characters")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    //@NotBlank(message = "Metadata cannot be blank")
    @Column(name = "metadata", columnDefinition = "xml")
    private String metadata;

    //@NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0", inclusive = true)
    @DecimalMax(value = "10000", inclusive = true)
    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    // region Constructors
    public Product() {
        super();
    }
    // endregion

    // region Getters and Setters
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

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
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
    // endregion
}