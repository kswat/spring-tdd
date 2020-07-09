package com.fictional.site.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fictional.site.service.ProductService;
@SpringBootTest
class ProductControllerTest {

	@MockBean
	private ProductService service;
	
	@Autowired
	private ProductController productController;
	
	@Test
	void testController() {
		assertNotNull(productController);
	}
}