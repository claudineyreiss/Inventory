package com.siersolutions.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.siersolutions.model.Product;
import com.siersolutions.repository.ProductRepository;
import com.siersolutions.repository.filter.ProductFilter;

@Named
@ViewScoped
public class ProductSearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private ProductRepository products;
	private ProductFilter filter;
	private List<Product> filterProducts;

	public ProductSearchBean() {
		filter = new ProductFilter();
	}

	public void search() {
		filterProducts = products.filterProduct(filter);
	}

	public List<Product> getFilterProducts() {
		return filterProducts;
	}

	public ProductFilter getFilter() {
		return filter;
	}
}
