package com.example.springbootelasearch.elas.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.example.springbootelasearch.elas.mapper"})
public class MyBatisConfig {
}
