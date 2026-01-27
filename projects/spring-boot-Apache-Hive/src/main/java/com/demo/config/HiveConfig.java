package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class HiveConfig {

    @Bean
    public DataSource hiveDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
        ds.setUrl("jdbc:hive2://localhost:10000/default");
        ds.setUsername("hive");
        ds.setPassword("");
        return ds;
    }
}
