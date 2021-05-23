package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_payment")
public class Payment extends BaseEntity {
	@Column(name = "total")
	private double total;
	@Column(name = "method")
	private String method;
	
	@OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
	private Order order;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipmentid", referencedColumnName = "id")
	private Shipment shipment;
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	
	
	
	
}
