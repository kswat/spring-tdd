package com.fictional.site.service;

import java.util.List;
import java.util.Optional;

import com.fictional.site.model.Product;

public interface ProductService {

	public Optional<Product> findById(Integer id) ;

	public List<Product> findAll();

	public Product save(Product product);

	public boolean update(Product prod);

	public boolean delete(Integer id);

}
