package com.bearclawvisions.services.interfaces;

import com.bearclawvisions.dto.product.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(int id);
//    String getAdminProduct();
//    String getCustomerProduct();
    List<ProductDto> getAllProducts();
    List<ProductDto> getAllProductsByPage(int page, int pageSize);
    void deleteProductById(int id);
}
