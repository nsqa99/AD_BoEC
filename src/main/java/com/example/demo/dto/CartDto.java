package com.example.demo.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;

public class CartDto extends AbstractDTO<Cart> {
	private double total;
	private List<ItemCartDto> items;
	
	
	
	public CartDto() {
	}



	public CartDto(Cart cart) {
		this.setId(cart.getId());
		this.total = cart.getTotal();
		this.items = cart.getItems().stream().map(item -> new ItemCartDto(item)).collect(Collectors.toList());
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public List<ItemCartDto> getItems() {
		return items;
	}



	public void setItems(List<ItemCartDto> items) {
		this.items = items;
	}
	
	
	

}
