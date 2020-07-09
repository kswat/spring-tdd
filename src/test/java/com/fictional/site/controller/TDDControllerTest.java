package com.fictional.site.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TDDControllerTest {


	@Autowired
	private TDDController tddController;
	
	@Test
	void testController() {
		assertNotNull(tddController);
	}
}
