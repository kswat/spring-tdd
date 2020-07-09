package com.fictional.site.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fictional.site.model.Product;
import com.fictional.site.service.ProductService;

@RestController
public class ProductController {
	
	private static final Logger logger = LogManager.getLogger(ProductController.class);
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
        this.productService = productService;
    }
	  
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Integer id) {
		 return productService.findById(id)
				 .map( product -> {
					 try {
						 return ResponseEntity
								 .ok()
								 .eTag(Integer.toString(product.getVersion()))
								 .location(new URI("/product/" + product.getId()))
								 .body(product);
					 }catch(URISyntaxException e) {
						 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
					 }
				 })
		 		.orElse(ResponseEntity.notFound().build());
		 
	}
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productService.findAll();
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        logger.info("Creating new product with name: {}, quantity: {}", product.getName(), product.getQuantity());
        
        Product newProduct = productService.save(product);	        
        try {
        	return ResponseEntity
        			.created(new URI("/product/" + product.getId()))
        			.eTag(Integer.toString(newProduct.getVersion()))
        			.body(newProduct);
        } catch(URISyntaxException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }	        
	 }
}
