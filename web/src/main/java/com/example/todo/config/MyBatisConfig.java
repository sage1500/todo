package com.example.todo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.todo.domain.repository")
public class MyBatisConfig {
    
}