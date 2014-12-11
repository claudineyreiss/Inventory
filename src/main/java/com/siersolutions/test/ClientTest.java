package com.siersolutions.test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.siersolutions.model.Address;
import com.siersolutions.model.Client;
import com.siersolutions.model.PersonType;

public class ClientTest {

	public static void main(String[] args) {
	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("StockController");
		EntityManager em = factory.createEntityManager();
		
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		
		Client client = new Client();
		client.setName("rafael reis");
		client.setEmail("rafael@gmail.com");
		client.setType(PersonType.PRIVATE_INVIDUAL);
		
		Address address= new Address();
		address.setStreet("Metro Terrace");
		address.setNumber("101");
		address.setCity("Madison");
		address.setState("WI");
		address.setZipcode("53423");
		address.setClient(client);
		
		client.getAddresses().add(address);
		
		em.persist(client);

		System.out.println("saved with sucess");
		trx.commit();
	}
	
}
