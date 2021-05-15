package com.example.demo.dto;

import com.example.demo.entity.Publisher;

public class PublisherDto extends AbstractDTO<PublisherDto>{

	private String name;
	
	public PublisherDto() {
		// TODO Auto-generated constructor stub
	}

	public PublisherDto(Publisher entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
