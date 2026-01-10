package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.entitities.Product;

public interface IProductService {
    Product getProductById(int id);
    String getAdminProduct();
    String getCustomerProduct();
}
