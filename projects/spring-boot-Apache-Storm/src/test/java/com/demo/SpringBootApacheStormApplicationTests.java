package com.demo;

import com.demo.storm.StormTopologyRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpringBootApacheStormApplicationTests {

	@Mock
	private StormTopologyRunner stormTopologyRunner; // mock instead of @MockBean

	@Test
	void contextLoads() {
		// test logic
	}
}
