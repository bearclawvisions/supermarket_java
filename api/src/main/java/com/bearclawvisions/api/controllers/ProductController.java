package com.bearclawvisions.api.controllers;

import com.bearclawvisions.api.annotations.IsAdmin;
import com.bearclawvisions.api.contracts.ApiResponse;
import com.bearclawvisions.api.helpers.ApiResponseBuilder;
import com.bearclawvisions.dto.product.ProductDto;
import com.bearclawvisions.services.interfaces.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final String PRODUCT_ID = "/{id}";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @IsAdmin
    @GetMapping() // index
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProduct() {
        return ApiResponseBuilder.success(productService.getAllProducts());
    }

    @GetMapping(PRODUCT_ID)
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable int id) {
        return ApiResponseBuilder.success(productService.getProductById(id));
    }

    @DeleteMapping(PRODUCT_ID)
    public ResponseEntity<ApiResponse<String>> deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
        return ApiResponseBuilder.success("Product deleted successfully");
    }
//
//    @IsAdmin // can be applied to controller level as well, just like C#
//    @GetMapping("/adminget")
//    public ResponseEntity<ApiResponse<String>> getAdminProduct() {
//        return ApiResponseBuilder.success(productService.getAdminProduct());
//    }
//
//    @IsCustomer
//    @GetMapping("/customerget")
//    public ResponseEntity<ApiResponse<String>> getCustomerProduct() {
//        return ApiResponseBuilder.success(productService.getCustomerProduct());
//    }
}
