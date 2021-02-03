package com.example.springbootmybits.mybatis.dataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {
    static final String PACKAGE = "com.example.springbootmybits.mybatis.oracleMapper";
    static final String MAPPER_LOCATION ="classpath:mapper/oracle/*.xml";

    @Bean(value = "oracleDatasource")
    @ConfigurationProperties(value = "spring.datasource.druid.oracle")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "oracleTransactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(value = "oracleSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oracleDatasource")DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        return sqlSessionFactory.getObject();
    }

}
