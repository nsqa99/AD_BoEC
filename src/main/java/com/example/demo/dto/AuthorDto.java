package com.example.demo.dto;

import com.example.demo.entity.Author;

public class AuthorDto extends AbstractDTO<AuthorDto> {

	private String name;

	public AuthorDto() {
		// TODO Auto-generated constructor stub
	}

	public AuthorDto(Author entity) {
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
