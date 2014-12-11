package com.siersolutions.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_creation", nullable = false)
	private Date dateCreate;
	@Column(columnDefinition = "text")
	private String observation;
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "date_delivery", nullable = false)
	private Date dateDelivery;
	@NotNull
	@Column(name = "delivery_price", nullable = false, precision = 10, scale = 2)
	private BigDecimal valueDelivery;
	@NotNull
	@Column(name = "value_discount", nullable = false, precision = 10, scale = 2)
	private BigDecimal valueDiscount;
	@NotNull
	@Column(name = "value_total", nullable = false, precision = 10, scale = 2)
	private BigDecimal valueTotal;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private OrderStatus status;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PaymentType paymentType;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private Seller seller;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	@Embedded
	private AddressDelivery addressDelivery;
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> items = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public BigDecimal getValueDelivery() {
		return valueDelivery;
	}

	public void setValueDelivery(BigDecimal valueDelivery) {
		this.valueDelivery = valueDelivery;
	}

	public BigDecimal getValueDiscount() {
		return valueDiscount;
	}

	public void setValueDiscount(BigDecimal valueDiscount) {
		this.valueDiscount = valueDiscount;
	}

	public BigDecimal getValueTotal() {
		return valueTotal;
	}

	public void setValueTotal(BigDecimal valueTotal) {
		this.valueTotal = valueTotal;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public AddressDelivery getAddressDelivery() {
		return addressDelivery;
	}

	public void setAddressDelivery(AddressDelivery addressDelivery) {
		this.addressDelivery = addressDelivery;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItem(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}