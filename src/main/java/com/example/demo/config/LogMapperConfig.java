package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(
    basePackages = "com.example.demo.dao.log",
    sqlSessionFactoryRef = "logSqlSessionFactory"
)
public class LogMapperConfig {
    
    @Bean(name = "logDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.log")
    public DataSource logDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "logSqlSessionFactory")
    public SqlSessionFactory logSqlSessionFactory(@Qualifier("logDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/log/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
