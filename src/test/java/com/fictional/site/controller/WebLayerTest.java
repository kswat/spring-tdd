package com.fictional.site.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//Narrow the tests to only the web layer by using @WebMvcTest,

@WebMvcTest
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/heartbeat");
		this.mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Hello World")));
	}
}
