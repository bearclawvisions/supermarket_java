package com.bearclawvisions.services.implementations;

import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.enums.ApplicationRole;
import com.bearclawvisions.ports.ProductCategoryRepository;
import com.bearclawvisions.ports.ProductRepository;
import com.bearclawvisions.services.interfaces.ProductService;
import com.bearclawvisions.services.interfaces.SecurityContextService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SecurityContextService securityContextService;

    public DefaultProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, SecurityContextService securityContextService) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.securityContextService = securityContextService;
    }

    @Cacheable(cacheNames = "supermarketCache", key = "'allProducts'")
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        var result = securityContextService.getCurrentUserId();
        var role = securityContextService.getCurrentUserRole();
        var email = securityContextService.getCurrentUserEmail();
        // todo validate request

        // map result to dto and convert xml to a model

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
