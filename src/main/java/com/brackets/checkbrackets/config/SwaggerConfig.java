package com.brackets.checkbrackets.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("brackets-api")
                .pathsToMatch("/api/**")
                .packagesToScan("com.brackets.checkbrackets.controller")
                .build();
    }
}