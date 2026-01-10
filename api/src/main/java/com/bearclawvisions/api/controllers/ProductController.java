package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.services.interfaces.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable int id) {
        return ApiResponseBuilder.success(productService.getProductById(id));
    }

    @GetMapping("products/adminget")
    //@PreAuthorize("hasRole(T(com.bearclawvisions.enums.ApplicationRole).ADMIN.name())") // this still uses magic strings. enums are not compile time constants in java. validate in service layer
    public ResponseEntity<ApiResponse<String>> getAdminProduct() {
        return ApiResponseBuilder.success(productService.getAdminProduct());
    }

    @GetMapping("products/customerget")
    public ResponseEntity<ApiResponse<String>> getCustomerProduct() {
        return ApiResponseBuilder.success(productService.getCustomerProduct());
    }
}
