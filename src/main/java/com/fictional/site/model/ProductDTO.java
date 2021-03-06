package com.fictional.site.model;

public class ProductDTO {

	private Integer id;
    private String name;
    private Integer quantity;
    private Integer version;
    
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
	
	public ProductDTO() {		
	}
	
	public ProductDTO(Integer id, String name, Integer quantity, Integer version) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.version = version;
	}
	
	
	public ProductDTO(String name, Integer quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	
	public ProductDTO(Integer id, String name, Integer quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
}
