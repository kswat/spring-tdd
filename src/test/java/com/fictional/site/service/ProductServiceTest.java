package com.fictional.site.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fictional.site.entity.Product;
import com.fictional.site.model.ProductDTO;
import com.fictional.site.repository.ProductRepository;

@SpringBootTest
class ProductServiceTest {

//  The service that we want to test.
    @Autowired
    private ProductService service;

//   A mock version of the ProductRepository for use in our tests. This is required for service constructor arg
    @MockBean
    private ProductRepository repository;
    
	@Autowired
    private ModelMapper modelMapper;
    
	@Test
	@DisplayName("Test findById Success")
	void testFindByIdSuccess() {
		ProductDTO mockProduct = new ProductDTO(1, "Product A", 10, 1);
		doReturn(Optional.of(mockProduct)).when(repository).findById(1);
		
		Optional<ProductDTO> responseProduct = service.findById(1);
		
		Assertions.assertTrue(responseProduct.isPresent(), "Product was not found");
        Assertions.assertSame(mockProduct,responseProduct.get(), "Products should be the same");		
	}

	@Test
	@DisplayName("Test findById empty")
	void testFindByIdEmpty() {
		ProductDTO mockProduct = new ProductDTO(1, "Product A", 10, 1);
		doReturn(Optional.empty()).when(repository).findById(100);
		
		Optional<ProductDTO> responseProduct = service.findById(100);
		
		Assertions.assertTrue(responseProduct.isEmpty(), "Product exists" );	
		// Assert the response - Alternate
//        Assertions.assertFalse(returnedProduct.isPresent(), "Product was found, when it shouldn't be");
	}
	
	@Test
	@DisplayName("Test update product Success")
	void testUpdateByIdSuccess() {
		ProductDTO mockProductDTO = new ProductDTO(1, "Product A", 10, 1);
		Product mockProduct = modelMapper.map(mockProductDTO, Product.class);
		Product afterUpdateProduct = new Product(1, "Product B", 10, 1);
		ProductDTO mockUpdateProductDTO = new ProductDTO(1, "Product B", 10, 2);
		doReturn(Optional.of(mockProduct)).when(repository).findById(1);
		doReturn(afterUpdateProduct).when(repository).save(any(Product.class));
		
		boolean updatedFlag = service.update(mockUpdateProductDTO);//service.findById(1).get());
		
		Assertions.assertTrue(updatedFlag, "Product was not found");	
	}
	
	  @Test
	    @DisplayName("Test findAll")
	    void testFindAll() {
	        // Setup our mock
	        Product mockProduct = new Product(1, "Product Name", 10, 1);
	        Product mockProduct2 = new Product(2, "Product Name 2", 15, 3);
	        doReturn(Arrays.asList(mockProduct, mockProduct2)).when(repository).findAll();

	        // Execute the service call
	        List<ProductDTO> products = service.findAll();

	        Assertions.assertEquals(2, products.size(), "findAll should return 2 products");
	    }

	    @Test
	    @DisplayName("Test save product")
	    void testSave() {
	        ProductDTO mockProduct = new ProductDTO(1, "Product Name", 10);
	        Product product = new Product(1, "Product Name", 10, 1);
	        doReturn(product).when(repository).save(any());

	        ProductDTO returnedProduct = service.save(mockProduct);

	        Assertions.assertNotNull(returnedProduct, "The saved product should not be null");
	        Assertions.assertEquals(1, returnedProduct.getVersion().intValue(),
	                "The version for a new product should be 1");
	    }
}
