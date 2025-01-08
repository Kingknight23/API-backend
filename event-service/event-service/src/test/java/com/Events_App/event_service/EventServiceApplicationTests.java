package com.Events_App.event_service;

import com.Events_App.event_service.dto.EventRequest;
import com.Events_App.event_service.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.google.common.net.MediaType;
import org.springframework.http.MediaType;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class EventServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDDBContainer = new MongoDBContainer("mongo:6.0.4")
			.waitingFor(Wait.forListeningPort())
			.withReuse(true);
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EventRepository eventRepository;
	@DynamicPropertySource
	static void setPropertiees(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDDBContainer::getReplicaSetUrl);
		dynamicPropertyRegistry.add("spring.mongodb.connection-timeout", () -> "20000"); // 20 seconds
		dynamicPropertyRegistry.add("spring.mongodb.socket-timeout", () -> "20000"); // 20 seconds

	}


	@Test
	void testCreateProduct() throws Exception {
		EventRequest eventRequest =getEventRequest();
		String eventRequestString = objectMapper.writeValueAsString(eventRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/event")
						.contentType(MediaType.APPLICATION_JSON)
						.content(eventRequestString))
				.andExpect(status().isCreated());
        Assertions.assertEquals(2, eventRepository.findAll().size());
	}

	private EventRequest getEventRequest(){
		return EventRequest.builder()
				.title("t1")
				.description("test 1 des")
				.price(BigDecimal.valueOf(30))
				.build();
	}

}
