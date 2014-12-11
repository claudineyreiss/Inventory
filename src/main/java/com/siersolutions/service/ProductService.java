package com.siersolutions.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.siersolutions.model.Product;
import com.siersolutions.repository.ProductRepository;
import com.siersolutions.util.jpa.Transactional;

public class ProductService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductRepository products;
	
	@Transactional
	public Product save(Product product){
		Product productExist = products.bySKU(product.getSku());
		if(productExist != null){
			throw new BussinessException("Product already exist a product with this SKU.");
		}
		return products.add(product);
		
	}
}
