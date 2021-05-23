package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_item_cart")
public class ItemCart extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "itemid", referencedColumnName = "id", nullable=false)
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "cartid", referencedColumnName = "id", nullable=false)
	private Cart cart;
	
	@Column(name = "amount", nullable=false)
	private int amount;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	

}
