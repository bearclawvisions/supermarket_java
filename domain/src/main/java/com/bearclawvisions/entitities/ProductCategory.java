package com.bearclawvisions.entitities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name", length = 50)
    private String name;

    @Column(nullable = false, name = "description", length = 255)
    private String description;

    // region Constructors
    public ProductCategory() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // endregion
}
