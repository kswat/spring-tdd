package com.fictional.site.service;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fictional.site.model.Product;
import com.fictional.site.repository.ProductRepository;

@SpringBootTest
class ProductServiceTest {

//  The service that we want to test.
    @Autowired
    private ProductService service;

//   A mock version of the ProductRepository for use in our tests. This is required for service constructor arg
    @MockBean
    private ProductRepository repository;
    
	@Test
	@DisplayName("Test findById Success")
	void testFindByIdSuccess() {
		Product mockProduct = new Product(1, "Product A", 10, 1);
		doReturn(Optional.of(mockProduct)).when(repository).findById(1);
		
		Optional<Product> responseProduct = service.findById(1);
		
		Assertions.assertTrue(responseProduct.isPresent(), "Product was not found");
        Assertions.assertSame(responseProduct.get(), mockProduct, "Products should be the same");		
	}

	@Test
	@DisplayName("Test findById empty")
	void testFindByIdEmpty() {
		Product mockProduct = new Product(1, "Product A", 10, 1);
		doReturn(Optional.empty()).when(repository).findById(100);
		
		Optional<Product> responseProduct = service.findById(100);
		
		Assertions.assertTrue(responseProduct.isEmpty(), "Product exists" );		
	}
	
	@Test
	@DisplayName("Test update product Success")
	void testUpdateByIdSuccess() {
		Product mockProduct = new Product(1, "Product A", 10, 1);
		doReturn(Optional.of(mockProduct)).when(repository).findById(1);
		doReturn(true).when(repository).update(mockProduct);
		
		boolean updatedFlag = service.update(service.findById(1).get());
		
		Assertions.assertTrue(updatedFlag, "Product was not found");	
	}
}
