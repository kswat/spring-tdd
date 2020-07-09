package com.fictional.site.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.containsString;

//The full Spring application context is started but without the server

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/heartbeat");
		this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Hello World")));
	}
}
