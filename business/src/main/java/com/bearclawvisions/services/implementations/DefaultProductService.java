package com.bearclawvisions.services.implementations;

import com.bearclawvisions.dto.product.ProductDto;
import com.bearclawvisions.entitities.Product;
import com.bearclawvisions.ports.ProductCategoryRepository;
import com.bearclawvisions.ports.ProductRepository;
import com.bearclawvisions.services.interfaces.ProductService;
import com.bearclawvisions.services.interfaces.SecurityContextService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SecurityContextService securityContextService;
    private final CacheManager cacheManager;

    private final String SUPERMARKET_CACHE = "supermarketCache";

    public DefaultProductService(ProductRepository productRepository,
                                 ProductCategoryRepository productCategoryRepository,
                                 SecurityContextService securityContextService,
                                 CacheManager cacheManager) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.securityContextService = securityContextService;
        this.cacheManager = cacheManager;
    }

    @Cacheable(cacheNames = SUPERMARKET_CACHE, key = "'allProducts'")
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(ProductDto::toDto)
                .collect(Collectors.toList());
    }

    // CacheEvict like in the same class is not working. It works if it was in a different class.
    //@CacheEvict(cacheNames = "supermarketCache", key = "'allProducts'")
    @Override
    public void deleteProductById(int id) {
        Cache cache = cacheManager.getCache(SUPERMARKET_CACHE);
        if (cache != null) {
            cache.clear(); // work around to clear cache
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductDto getProductById(int id) {
        var result = securityContextService.getCurrentUserId();
        var role = securityContextService.getCurrentUserRole();
        var email = securityContextService.getCurrentUserEmail();
        // todo validate request

        Product product = productRepository.findById(id);
        ProductDto productDto = ProductDto.toDto(product);

        return productDto;
    }

    // endpoints already secured by spring security. Logic wont enter here
//    @Override
//    public String getAdminProduct() {
//        securityContextService.validateUserRole(ApplicationRole.ADMIN);
//        return "Admin product details";
//    }
//
//    @Override
//    public String getCustomerProduct() {
//        securityContextService.validateUserRole(ApplicationRole.CUSTOMER);
//        return "Customer product details";
//    }
}
