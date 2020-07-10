package com.fictional.site.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fictional.site.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

	Optional<Product> findById(Integer id);

	List<Product> findAll();

//	boolean update(Product prod);

//	boolean delete(Integer id);

	Product save(Product product);

}
