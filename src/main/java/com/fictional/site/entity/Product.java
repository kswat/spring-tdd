package com.fictional.site.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	private Integer id;
    private String name;
    private Integer quantity;
    private Integer version;
    
    public Product() {
    	
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Product(Integer id, String name, Integer quantity, Integer version) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.version = version;
	}
    
}
