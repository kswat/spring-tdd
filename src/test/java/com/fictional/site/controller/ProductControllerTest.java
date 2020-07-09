package com.fictional.site.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fictional.site.model.Product;
import com.fictional.site.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

	@MockBean
	private ProductService service;
	
	@Autowired
	private ProductController productController;
	
   @Autowired
   private MockMvc mockMvc;
	
	@Test
	void testController() {
		assertNotNull(productController);
	}
	
	@Test
	@DisplayName("GET /product/1 - Not Found")
	void testGetProductByIdNotFound() throws Exception {
		doReturn(Optional.empty()).when(service).findById(1);
		mockMvc.perform(get("/product/{id}", 1))
			.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("GET /product/1 - Found")
	void testGetProductByIdFound() throws Exception {
		Product mockProduct = new Product(1,"Product A",10,1);
		doReturn(Optional.of(mockProduct)).when(service).findById(1);
		mockMvc.perform(get("/product/{id}", 1))
		
			.andExpect(status().isOk())			
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			
			.andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
			
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("Product A")));
	}
}