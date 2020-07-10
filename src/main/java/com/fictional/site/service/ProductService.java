package com.fictional.site.service;

import java.util.List;
import java.util.Optional;

import com.fictional.site.model.ProductDTO;

public interface ProductService {

	public Optional<ProductDTO> findById(Integer id) ;

	public List<ProductDTO> findAll();

	public ProductDTO save(ProductDTO product);

	public boolean update(ProductDTO prod);

	public boolean delete(Integer id);

}
