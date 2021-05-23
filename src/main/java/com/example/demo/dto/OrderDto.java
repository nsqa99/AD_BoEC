package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
import com.example.demo.entity.Shipment;
import com.example.demo.entity.User;

public class OrderDto extends AbstractDTO<Order> {
	private UserDto user;
	private Date createdAt;
	private CartDto cart;
	private PaymentDto payment;
	public OrderDto() {
	}
	
	public OrderDto(Order order) {
		super();
		this.setId(order.getId());
		this.user = new UserDto(order.getUser());
		this.cart = new CartDto(order.getCart());
		this.payment = new PaymentDto(order.getPayment());
		this.createdAt = order.getCreateAt();
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public CartDto getCart() {
		return cart;
	}

	public void setCart(CartDto cart) {
		this.cart = cart;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}
	
	
	
}
