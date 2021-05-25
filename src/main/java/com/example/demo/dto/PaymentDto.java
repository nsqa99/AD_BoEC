package com.example.demo.dto;

import com.example.demo.entity.Payment;

public class PaymentDto extends AbstractDTO<Payment>  {

	private String method;
	private double total;
	private ShipmentDto shipment;
	public PaymentDto(Payment payment) {
		super();
		this.setId(payment.getId());
		this.method = payment.getMethod();
		this.total = payment.getTotal();
		this.shipment = new ShipmentDto(payment.getShipment());
	}
	
	public PaymentDto() {
		super();
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ShipmentDto getShipment() {
		return shipment;
	}
	public void setShipment(ShipmentDto shipment) {
		this.shipment = shipment;
	}
	
	

}
