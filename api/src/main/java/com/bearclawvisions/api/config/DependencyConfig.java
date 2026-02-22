package com.bearclawvisions.api.config;

import com.bearclawvisions.repositories.ProductCategoryRepository;
import com.bearclawvisions.repositories.ProductRepository;
import com.bearclawvisions.repositories.UserRepository;
import com.bearclawvisions.services.implementations.DefaultProductService;
import com.bearclawvisions.services.implementations.DefaultSecurityContextService;
import com.bearclawvisions.services.implementations.UserDetailService;
import com.bearclawvisions.services.implementations.DefaultUserService;
import com.bearclawvisions.services.interfaces.ProductService;
import com.bearclawvisions.services.interfaces.SecurityContextService;
import com.bearclawvisions.services.interfaces.UserService;
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
    public SecurityContextService securityContextService() {
        return new DefaultSecurityContextService();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new DefaultUserService(userRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, SecurityContextService securityContextService) {
        return new DefaultProductService(productRepository, productCategoryRepository, securityContextService);
    }

    @Bean
    public UserDetailService userDetailService(UserRepository userRepository) {
        return new UserDetailService(userRepository);
    }
}
