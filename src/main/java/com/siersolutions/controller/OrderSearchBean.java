package com.siersolutions.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class OrderSearchBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> orderFilter;

	public OrderSearchBean() {
		orderFilter = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			orderFilter.add(i);
		}
	}

	public List<Integer> getOrderFilter() {
		return orderFilter;
	}

}
