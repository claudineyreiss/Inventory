package com.siersolutions.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.siersolutions.model.AddressDelivery;
import com.siersolutions.model.Order;
import com.siersolutions.service.BussinessException;


@Named
@ViewScoped
public class OrderBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Integer> items;
	private Order order;
	
	public OrderBean() {
		order = new Order();
		order.setAddressDelivery(new AddressDelivery());
		items = new ArrayList<Integer>();
		items.add(1);
	}
	
	public List<Integer> getItems(){
		return items;
	}
	
	public void save(){
		throw new BussinessException("Error trying to save the order");
	}
	
	public Order getOrder(){
		return order;
	}
}
