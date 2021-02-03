package com.example.springbootmybits.mybatis.dataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Primary
    @Bean(name = "mysqldatasource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "oracledatasource")
    @ConfigurationProperties("spring.datasource.druid.oracle")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "mysqlJdbcTemplate")
    public JdbcTemplate primaryTemplate(@Qualifier(value = "mysqldatasource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(value = "oracleJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier(value = "oracledatasource")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
