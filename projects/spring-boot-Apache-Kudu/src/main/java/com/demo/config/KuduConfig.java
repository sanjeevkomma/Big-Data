package com.demo.config;

import org.apache.kudu.client.KuduClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KuduConfig {

    @Value("${kudu.master}")
    private String kuduMaster;

    @Bean
    public KuduClient kuduClient() {
        return new KuduClient.KuduClientBuilder(kuduMaster)
                .defaultSocketReadTimeoutMs(60000)
                .build();
    }
}
