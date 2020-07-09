package com.fictional.site.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heartbeat")
public class TDDController {
	
	@GetMapping()
	public String getProduct() {
		return "Hello World";
	}
	
}
