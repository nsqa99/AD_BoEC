package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product_stock")
public class ProductStock extends BaseEntity {

	@Column(name = "quantity")
	private int quantity;

//	@OneToOne
//	@MapsId
//	@JoinColumn(name = "product_id")
//	private Product product;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}

}
