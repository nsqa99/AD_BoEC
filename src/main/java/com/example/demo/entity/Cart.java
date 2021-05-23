package com.example.demo.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cart")
public class Cart extends BaseEntity {
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	private List<ItemCart> items;
//	private List<Item> items;
	@Column(name = "total")
	private double total = 0;
	@OneToOne(mappedBy = "cart")
	private Order order;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
	private User user;
	
	public List<ItemCart> getItems() {
		return items;
	}
	public void setItems(List<ItemCart> items) {
		this.items = items;
		items.stream().forEach(item -> {
			this.total += item.getItem().getPrice() * item.getAmount();
		});
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getTotal() {
		return total;
	}
	
	
	
}
