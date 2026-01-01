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
}
