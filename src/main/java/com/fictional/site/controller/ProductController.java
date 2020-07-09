package com.fictional.site.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fictional.site.model.Product;
import com.fictional.site.service.ProductService;

@RestController
public class ProductController {
	
	private static final String PRODUCT_ROOT = "/product/";
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
								 .location(new URI(PRODUCT_ROOT + product.getId()))
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
        			.created(new URI(PRODUCT_ROOT + product.getId()))
        			.eTag(Integer.toString(newProduct.getVersion()))
        			.body(newProduct);
        } catch(URISyntaxException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }	        
	 }
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Integer id,
											@RequestHeader("If-Match") Integer ifMatch) {
		logger.info("Updating product with id: {}, name: {}, quantity: {}", id, product.getName(), product.getQuantity());
		
		 // Get the existing product
        Optional<Product> existingProduct = productService.findById(id);
        
        //compare eTags
        return existingProduct.map(prod ->{
        	logger.info("Product with ID: " + id + " has a version of " + prod.getVersion() + ". Update is for If-Match: " + ifMatch);
        	if(!prod.getVersion().equals(ifMatch)) {
        		return ResponseEntity.status(HttpStatus.CONFLICT).build();
        	}
        	        	
        	prod.setName(product.getName());
        	prod.setQuantity(product.getQuantity());
        	prod.setVersion(prod.getVersion()+1);
        	
        	logger.info("Product with ID: " + prod.getId()+ " has a version of " + prod.getVersion() + ". quantity : " + prod.getQuantity());
        
        	try {
        		 if (productService.update(prod)) {
        			 return ResponseEntity.ok().location(new URI(PRODUCT_ROOT+ prod.getId()))
        					 .eTag(Integer.toString(prod.getVersion()))
        					 .body(prod);
        		 }
        		 else {
        			 return ResponseEntity.notFound().build();
        		 }
        	} catch (URISyntaxException e) {
                // An error occurred trying to create the location URI, return an error
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
	}
	
	   @DeleteMapping("/product/{id}")
	    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {

	        logger.info("Deleting product with ID {}", id);

	        // Get the existing product
	        Optional<Product> existingProduct = productService.findById(id);

	        return existingProduct.map(p -> {
	            if (productService.delete(p.getId())) {
	                return ResponseEntity.ok().build();
	            } else {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	            }
	        }).orElse(ResponseEntity.notFound().build());
	    }
}
