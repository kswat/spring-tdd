package com.fictional.site.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fictional.site.entity.Product;
import com.fictional.site.model.ProductDTO;
import com.fictional.site.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired
    private ModelMapper modelMapper;
	
	private final ProductRepository productRepo;
	
	public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepo = productRepository;
    }
	
	@Override
	public Optional<ProductDTO> findById(Integer id) {
		logger.info("Find product with id: {}", id);
		return convertToDto(productRepo.findById(id));
	}

	@Override
	public List<ProductDTO> findAll() {
		logger.info("Find all products");
		return convertListToDto(productRepo.findAll());
	}

	@Override
	public ProductDTO save(ProductDTO productdto) {
		// Set the product version to 1 as we're adding a new product to the database        
        Product product = convertToEntity(productdto);
        product.setVersion(1);
        logger.info("Save product to the database: {}", product);
        
        return convertToDto(productRepo.save(product));
	}

	@Override
	public boolean update(ProductDTO prodDTO) {
		Product product = convertToEntity(prodDTO);
		logger.info("Update product: {}", product);
		Product newProduct = productRepo.save(product);
		if(newProduct.getId().equals(product.getId())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		logger.info("Delete product: {}", id);
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent()) {
			//delete
			productRepo.delete(product.get());
			return true;
		}
		return false; //productRepo.delete(id);
	}

	public List<ProductDTO> convertListToDto(List<Product> products) {
		return products.stream().map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
	}
	private Optional<ProductDTO> convertToDto(Optional<Product> product) {
		if(product.isPresent()) {
			return Optional.of( modelMapper.map(product.get(), ProductDTO.class));
		}
	    return Optional.empty();
	}
	private ProductDTO convertToDto(Product product) {
		ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
	    return productDto;
	}
	private Product convertToEntity(ProductDTO productDto) {
		Product product = modelMapper.map(productDto, Product.class);	    
	    return product;
	}
}
