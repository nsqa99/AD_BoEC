//package com.example.demo.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;

//@Entity
//@Table(name = "tbl_brand")
//public class Brand extends BaseEntity {
//
//	@Column(name = "name")
//	private String name;
//
//	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Item> product = new ArrayList<>();
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public List<Item> getProduct() {
//		return product;
//	}
//
//	public void setProduct(List<Item> product) {
//		this.product = product;
//	}
//
//}
