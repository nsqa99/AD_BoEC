package com.example.demo.dto;

import com.example.demo.entity.ItemCart;

public class ItemCartDto extends AbstractDTO<ItemCart> {
	private ItemDto item;
	private int amount;
	public ItemCartDto(ItemCart itemCart) {
		super();
		this.setId(itemCart.getId());
		this.item = new ItemDto(itemCart.getItem());
		this.amount = itemCart.getAmount();
	}
	
	public ItemCartDto() {
		super();
	}

	public ItemDto getItem() {
		return item;
	}
	public void setItem(ItemDto item) {
		this.item = item;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
