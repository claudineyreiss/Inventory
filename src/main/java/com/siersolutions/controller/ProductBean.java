package com.siersolutions.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.siersolutions.model.Category;
import com.siersolutions.model.Product;
import com.siersolutions.repository.CategoryRepository;
import com.siersolutions.service.ProductService;
import com.siersolutions.util.FacesUtil;

@Named
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CategoryRepository categories;

	@Inject
	private ProductService productService;

	private Category categoryParent;
	private Product product;

	private List<Category> categoriesRoot;
	private List<Category> subCategories;
	
	public ProductBean() {
		cleanForm();
	}
	
	private void cleanForm() {
		product = new Product();
		categoryParent = null;
		subCategories = new ArrayList<Category>();
	}

	public void save() {
		product = productService.save(product);
		cleanForm();
		FacesUtil.addInfoMessage("Product save with success");
	}

	public void init() {
		System.out.println("initializing...");

		if (!FacesUtil.isPostBack()) {
			categoriesRoot = categories.findRootCategory();
			if (this.categoryParent != null) {
				loadSubcategory();
			}
		}
	}

	public void loadSubcategory() {
		subCategories = categories.subcategoriesOf(categoryParent);
	}

	public void setProduct(Product product) {
		this.product = product;
		if (this.product != null) {
			this.categoryParent = this.product.getCategory()
					.getCategoryParent();
			System.out.println(product.getSku());
		}
	}

	public Product getProduct() {
		return product;
	}

	public List<Category> getCategoriesRoot() {
		return categoriesRoot;
	}

	public Category getCategoryParent() {
		return categoryParent;
	}

	public void setCategoryParent(Category categoryParent) {
		this.categoryParent = categoryParent;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}
}
