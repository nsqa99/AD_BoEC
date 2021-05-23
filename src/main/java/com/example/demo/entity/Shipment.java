package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_shipment")
public class Shipment extends BaseEntity {
	@Column(name = "ship_fee")
	private double shipFee;
	@Column(name = "ship_company")
	private String shipCompany;
	@Column(name = "ship_type")
	private String shipType;
	@Column(name = "ship_address", nullable = false)
	private String shipAddress;
	@OneToOne(mappedBy = "shipment")
	private Payment payment;
	
	public double getShipFee() {
		return shipFee;
	}
	public void setShipFee(double shipFee) {
		this.shipFee = shipFee;
	}
	public String getShipCompany() {
		return shipCompany;
	}
	public void setShipCompany(String shipCompany) {
		this.shipCompany = shipCompany;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
}
