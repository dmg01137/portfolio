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
    basePackages = "com.example.demo.dao.detection",
    sqlSessionFactoryRef = "detectionSqlSessionFactory"
)
public class DetectionMapperConfig {
	
	 @Bean(name = "detectionDataSource")
	    @ConfigurationProperties(prefix = "spring.datasource.detection")
	    public DataSource detectionDataSource() {
	        return DataSourceBuilder.create().build();
	    }

	  
	    @Bean(name = "detectionSqlSessionFactory")
	    public SqlSessionFactory detectionSqlSessionFactory(@Qualifier("detectionDataSource") DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	        sqlSessionFactoryBean.setDataSource(dataSource);
	        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
	                .getResources("classpath:mapper/detection/*.xml"));
	        return sqlSessionFactoryBean.getObject();
	    }
}
