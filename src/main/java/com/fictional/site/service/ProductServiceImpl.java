package com.fictional.site.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fictional.site.model.Product;
import com.fictional.site.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	
	private final ProductRepository productRepo;
	
	public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepo = productRepository;
    }
	
	@Override
	public Optional<Product> findById(Integer id) {
		logger.info("Find product with id: {}", id);
		return productRepo.findById(id);
	}

	@Override
	public List<Product> findAll() {
		logger.info("Find all products");
		return productRepo.findAll();
	}

	@Override
	public Product save(Product product) {
		// Set the product version to 1 as we're adding a new product to the database
        product.setVersion(1);

        logger.info("Save product to the database: {}", product);
        return productRepo.save(product);
	}

	@Override
	public boolean update(Product prod) {
		logger.info("Update product: {}", prod);
		return productRepo.update(prod);
	}

	@Override
	public boolean delete(Integer id) {
		logger.info("Delete product: {}", id);
		return productRepo.delete(id);
	}

}
