package com.fictional.site.service;

import java.util.Optional;

import com.fictional.site.model.Product;

public interface ProductService {

	public Optional<Product> findById(Integer id) ;

	public Iterable<Product> findAll();

	public Product save(Product product);

}
