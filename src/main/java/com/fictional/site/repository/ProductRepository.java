package com.fictional.site.repository;

import java.util.Optional;

import com.fictional.site.model.Product;

public interface ProductRepository {

	Optional<Product> findById(Integer id);

	Iterable<Product> findAll();

	boolean update(Product prod);

}
