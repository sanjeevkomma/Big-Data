package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HadoopConfig {

    @Bean
    public org.apache.hadoop.conf.Configuration hadoopConfiguration() {

        org.apache.hadoop.conf.Configuration config =
                new org.apache.hadoop.conf.Configuration();

        config.set("fs.defaultFS", "hdfs://localhost:9000");
        return config;
    }
}
