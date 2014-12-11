package com.siersolutions.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class AddressDelivery implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 150)
	@Column(name = "delivery_street", length = 150)
	private String street;
	@NotBlank
	@Size(max = 20)
	@Column(name = "delivery_number", nullable = false, length = 20)
	private String number;
	@NotBlank
	@Size(max = 150)
	@Column(name = "delivery_city", nullable = false, length = 60)
	private String city;
	@NotBlank
	@Size(max = 30)
	@Column(name = "delivery_state", nullable = false, length = 60)
	private String state;
	@NotBlank
	@Size(max = 10)
	@Column(name = "delivery_zipcode", nullable = false, length = 9)
	private String zipcode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
