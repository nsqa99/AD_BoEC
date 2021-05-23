package com.example.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_order")
public class Order extends BaseEntity {
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cartid", referencedColumnName = "id", nullable = false)
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentid", referencedColumnName = "id", nullable = false)
	private Payment payment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private User user;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Date createAt;

	
	public Order() {
		super();
	}
	
	

	public Order(Cart cart, Payment payment, User user) {
		super();
		this.cart = cart;
		this.payment = payment;
		this.user = user;
	}



	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Payment getPayment() {
		return payment;
	}
	
	

	public Date getCreateAt() {
		return createAt;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
