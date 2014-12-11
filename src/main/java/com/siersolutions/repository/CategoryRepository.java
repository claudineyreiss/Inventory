package com.siersolutions.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.siersolutions.model.Category;

public class CategoryRepository implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public List<Category> findRootCategory(){
		return em.createQuery("from Category where categoryParent is null", Category.class).getResultList();	
	}
	
	public List<Category>subcategoriesOf(Category categoryParent){
		return em.createQuery("from Category where categoryParent = :root", Category.class).setParameter("root", categoryParent).getResultList();
	}
	
	public Category byId(Long id){
		return em.find(Category.class, id);
	}
}
