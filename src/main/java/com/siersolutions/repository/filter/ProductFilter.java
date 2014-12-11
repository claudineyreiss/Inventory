package com.siersolutions.repository.filter;

import java.io.Serializable;

import com.siersolutions.validation.SKU;

public class ProductFilter implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@SKU
	private String sku;
	private String name;
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
