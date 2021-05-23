package com.example.demo.dto;

import com.example.demo.entity.Shipment;

public class ShipmentDto extends AbstractDTO<Shipment> {
	private double shipFee;
	private String shipCompany;
	private String shipType;
	private String shipAddress;
	
	public ShipmentDto(Shipment shipment) {
		this.setId(shipment.getId());
		this.shipFee = shipment.getShipFee();
		this.shipCompany = shipment.getShipCompany();
		this.shipType = shipment.getShipType();
		this.shipAddress = shipment.getShipAddress();
	}
	
	

	public ShipmentDto() {
		super();
	}



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
	
	
	
	

}
