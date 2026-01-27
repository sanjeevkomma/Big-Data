package com.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HadoopConfig {
    static {
        System.setProperty("hadoop.home.dir", "C:\\hadoop"); // Windows fix
    }
}
