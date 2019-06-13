package com.bcg.test.settleshop.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import snaq.db.DBPoolDataSource;

import java.beans.PropertyVetoException;

/**
 * Created by alexlu on 19/6/13
 */
@Data
@Configuration
@EnableAutoConfiguration
public class DBPoolConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;
//
//    @Value("${spring.datasource.initial-size}")
//    private int initialSize;
//    @Value("${spring.datasource.connection-timeout}")
//    private int connectionTimeout;
//    @Value("${spring.datasource.min-idle}")
//    private int minPoolSize;
//    @Value("${spring.datasource.max-active}")
//    private int maxPoolSize;
//    @Value("${spring.datasource.maxIdleTime}")
//    private int maxIdleTime;
//    @Value("${spring.datasource.validation-query}")
//    private String preferredTestQuery;

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dbPoolDataSource());
    }

    @Bean(name = "dbPoolDataSource")
    public DBPoolDataSource dbPoolDataSource() throws PropertyVetoException {
        DBPoolDataSource cpds = new DBPoolDataSource();
        cpds.setDriverClassName(driverClassName);
        cpds.setUrl(url);
        cpds.setUser(username);
        cpds.setPassword(password);
        cpds.setName("pool-ds");
//        cpds.setMinPool(minPoolSize);
//        cpds.setMaxPool(maxPoolSize);
//        cpds.setMaxSize(maxPoolSize);
//        cpds.setIdleTimeout(maxIdleTime);
//        cpds.setLoginTimeout(connectionTimeout);
//        cpds.setValidationQuery(preferredTestQuery);
        return cpds;
    }
}