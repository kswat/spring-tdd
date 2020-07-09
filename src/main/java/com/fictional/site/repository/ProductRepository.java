package com.fictional.site.repository;

import java.util.List;
import java.util.Optional;

import com.fictional.site.model.Product;

public interface ProductRepository {

	Optional<Product> findById(Integer id);

	List<Product> findAll();

	boolean update(Product prod);

	boolean delete(Integer id);

	Product save(Product product);

}
