package com.siersolutions.test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.siersolutions.model.Group;
import com.siersolutions.model.Seller;

public class SellerTest {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("StockController");
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		
		Seller s = new Seller();
		s.setName("Pedro");
		s.setEmail("s@gmail.com");
		s.setPassword("123");
		
		Group g = new Group();
		g.setName("sellers");
		g.setDescription("company seller");
		
		s.getGroups().add(g);
		em.persist(s);
		
		System.out.println("saved with success");
		trx.commit();
	}
	
}
