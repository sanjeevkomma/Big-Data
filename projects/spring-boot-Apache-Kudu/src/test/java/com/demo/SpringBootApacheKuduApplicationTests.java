package com.demo;

import com.demo.service.KuduService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringBootApacheKuduApplicationTests {

	// Test-specific configuration
	@TestConfiguration
	static class KuduTestConfig {
		@Bean
		public KuduService kuduService() {
			// Return a Mockito mock for testing
			return Mockito.mock(KuduService.class);
		}
	}

	@Test
	void contextLoads() {
	}

}
