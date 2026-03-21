package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.entitities.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(int id);
    String getAdminProduct();
    String getCustomerProduct();
    List<Product> getAllProducts();
    void deleteProductById(int id);
}
