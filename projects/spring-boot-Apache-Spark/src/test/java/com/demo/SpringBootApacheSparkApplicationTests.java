package com.demo;

import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringBootApacheSparkApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	static class TestConfig {

		@Bean
		SparkSession sparkSession() {
			return Mockito.mock(SparkSession.class);
		}
	}
}
