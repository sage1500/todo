package com.example.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = BaseSecurityConfig.class)
@MapperScan("com.example.security.domain.repository")
public class BaseSecurityConfig {
    
}