package com.siersolutions.test;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.siersolutions.model.Category;
import com.siersolutions.model.Product;

public class ProductTest {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("StockController");
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		
		// instanciamos a categoria pai (Bebidas)
		Category cParent = new Category();
		cParent.setDescription("Gaming");;
		
		// instanciamos a categoria filha (Refrigerantes)
		Category cChild = new Category();
		cChild.setDescription("Keyboard");
		cChild.setCategoryParent(cParent);
		
		// adicionamos a categoria Refrigerantes como filha de Bebidas
		cParent.getSubCategories().add(cChild);

		// ao persistir a categoria pai (Refrigerantes), a filha (Bebidas) 
		// deve ser persistida também
		em.persist(cParent);
		
		// instanciamos e persistimos um produto
		Product p = new Product();
		p.setCategory(cChild);
		p.setName("Sound Bar");
		p.setQuantityStock(10);
		p.setSku("SB0123");
		p.setUnitPrice(new BigDecimal(2.10));
		em.persist(p);
		
		System.out.println("saved with success");
		trx.commit();
	}
	
}
