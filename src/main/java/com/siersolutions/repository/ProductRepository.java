package com.siersolutions.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.siersolutions.model.Category;
import com.siersolutions.model.Product;
import com.siersolutions.repository.filter.ProductFilter;

public class ProductRepository implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Product add(Product product){
		return em.merge(product);
	}
	
	public Product bySKU(String sku){
		try{
			return em.createQuery("from Product where upper(sku) = :sku", Product.class).setParameter("sku", sku.toUpperCase()).getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product>filterProduct(ProductFilter filter){
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Product.class);
		
		if (StringUtils.isNotBlank(filter.getSku())) {
			criteria.add(Restrictions.eq("sku", filter.getSku()));
		}
		
		if (StringUtils.isNotBlank(filter.getName())) {
			criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("name")).list();
	}

	public Product byId(Long id) {
		return em.find(Product.class, id);
	}
}
