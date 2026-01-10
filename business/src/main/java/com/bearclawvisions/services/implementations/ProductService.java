package com.bearclawvisions.services.implementations;

import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.repositories.ProductCategoryRepository;
import com.bearclawvisions.repositories.ProductRepository;
import com.bearclawvisions.services.interfaces.IProductService;
import com.bearclawvisions.services.interfaces.ISecurityContextService;

public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ISecurityContextService securityContextService;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ISecurityContextService securityContextService) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.securityContextService = securityContextService;
    }

    @Override
    public Product getProductById(int id) {
        var result = securityContextService.getCurrentUserId();
        var role = securityContextService.getCurrentUserRole();
        var email = securityContextService.getCurrentUserEmail();
        // todo validate request
        return productRepository.findById(id);
    }

    @Override
    public String getAdminProduct() {
        securityContextService.validateUserRole(ApplicationRole.ADMIN);
        return "Admin product details";
    }

    @Override
    public String getCustomerProduct() {
        securityContextService.validateUserRole(ApplicationRole.CUSTOMER);
        return "Customer product details";
    }
}
