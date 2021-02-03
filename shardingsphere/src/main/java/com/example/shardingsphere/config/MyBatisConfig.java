package com.example.shardingsphere.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.example.shardingsphere.mapper"})
public class MyBatisConfig {
}
