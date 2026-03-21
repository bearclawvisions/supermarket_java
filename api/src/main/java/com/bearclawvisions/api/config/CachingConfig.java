package com.bearclawvisions.api.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CachingConfig {
    // no extra config for simple caching
}
