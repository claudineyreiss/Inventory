package com.siersolutions.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.siersolutions.model.AddressDelivery;
import com.siersolutions.model.Client;
import com.siersolutions.model.Order;
import com.siersolutions.model.OrderItem;
import com.siersolutions.model.OrderStatus;
import com.siersolutions.model.PaymentType;
import com.siersolutions.model.Product;
import com.siersolutions.model.Seller;

public class OrderTest {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("StockController");
		EntityManager em = factory.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		ts.begin();
		
		Client c = em.find(Client.class, 1L);
		Product p = em.find(Product.class, 5L);
		Seller s = em.find(Seller.class, 6L);
		
		
		AddressDelivery deliveryAddress = new AddressDelivery();
		deliveryAddress.setCity("Sun Prairie");
		deliveryAddress.setNumber("123");
		deliveryAddress.setStreet("Edmonton dr");
		deliveryAddress.setState("Wisconsin");
		deliveryAddress.setZipcode("43213");
		
		Order o = new Order();
		o.setClient(c);
		o.setDateCreate(new Date());
		o.setDateDelivery(new Date());
		o.setPaymentType(PaymentType.CASH);
		o.setObservation("bla bla bla bla bla bla bla");
		o.setStatus(OrderStatus.BUDGE);
		o.setValueDiscount(BigDecimal.ZERO);
		o.setValueDelivery(BigDecimal.ZERO);
		o.setValueTotal(new BigDecimal(55.2));
		o.setSeller(s);
		o.setAddressDelivery(deliveryAddress);
		
		OrderItem item = new OrderItem();
		item.setProduct(p);
		item.setQuantity(10);
		item.setUnitValue(new BigDecimal(10.2));
		item.setOrders(o);
		
		o.getItems().add(item);
		em.persist(o);
		System.out.println("saved with sucess");
		ts.commit();
	}
}
