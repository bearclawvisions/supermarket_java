package com.bearclawvisions.api.config;

import com.bearclawvisions.repositories.ProductCategoryRepository;
import com.bearclawvisions.repositories.ProductRepository;
import com.bearclawvisions.repositories.UserRepository;
import com.bearclawvisions.services.implementations.ProductService;
import com.bearclawvisions.services.implementations.SecurityContextService;
import com.bearclawvisions.services.implementations.UserDetailService;
import com.bearclawvisions.services.implementations.UserService;
import com.bearclawvisions.services.interfaces.IProductService;
import com.bearclawvisions.services.interfaces.ISecurityContextService;
import com.bearclawvisions.services.interfaces.IUserService;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.bearclawvisions.api",
        "com.bearclawvisions.repositories",
        "com.bearclawvisions.services"
})
@EnableJpaRepositories(basePackages = {
        "com.bearclawvisions.repositories"
})
@EntityScan(basePackages = {
        "com.bearclawvisions.entitities"
})
public class DependencyConfig {

    // services
    @Bean
    public ISecurityContextService securityContextService() {
        return new SecurityContextService();
    }

    @Bean
    public IUserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public IProductService productService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ISecurityContextService securityContextService) {
        return new ProductService(productRepository, productCategoryRepository, securityContextService);
    }

    @Bean
    public UserDetailService userDetailService(UserRepository userRepository) {
        return new UserDetailService(userRepository);
    }
}
