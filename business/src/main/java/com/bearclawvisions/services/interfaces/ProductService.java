package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.entitities.Product;

public interface ProductService {
    Product getProductById(int id);
    String getAdminProduct();
    String getCustomerProduct();
}
