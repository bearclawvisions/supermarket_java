package com.bearclawvisions.api.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.bearclawvisions.api",
        "com.bearclawvisions.adapters", // repo adapters
        "com.bearclawvisions.services"
})
@EnableJpaRepositories(basePackages = {
        "com.bearclawvisions.repositories"
})
@EntityScan(basePackages = {
        "com.bearclawvisions.entitities"
})
public class DependencyConfig {

    // in java the below is likely used for DI of external packages where custom implementations are needed
    // Component scan picks up the @Service and @Repository annotations
    // services
//    @Bean
//    public SecurityContextService securityContextService() {
//        return new DefaultSecurityContextService();
//    }
//
//    @Bean
//    public UserService userService(UserRepository userRepository) {
//        return new DefaultUserService(userRepository);
//    }
//
//    @Bean
//    public ProductService productService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, SecurityContextService securityContextService) {
//        return new DefaultProductService(productRepository, productCategoryRepository, securityContextService);
//    }
//
//    @Bean
//    public UserDetailService userDetailService(UserRepository userRepository) {
//        return new UserDetailService(userRepository);
//    }

//    // manual repo wiring
//    @Bean
//    public ProductCategoryRepository productCategoryRepository(JpaProductCategoryRepository jpaProductCategoryRepository) {
//        return new ProductCategoryRepositoryAdapter(jpaProductCategoryRepository);
//    }
}
