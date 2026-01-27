package com.demo.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class SparkConfig {

    @Bean(destroyMethod = "stop")
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("SpringBootSparkPOC")
                .master("local[*]")

                // 🔥 Kill ALL servlet-based Spark components
                .config("spark.ui.enabled", "false")
                .config("spark.metrics.conf.*.sink.servlet.class", "")

                // Performance + Windows safety
                .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
                .config("spark.hadoop.fs.file.impl", "org.apache.hadoop.fs.LocalFileSystem")

                .getOrCreate();
    }
}


