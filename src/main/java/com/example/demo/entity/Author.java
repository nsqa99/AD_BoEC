package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_author")
public class Author extends BaseEntity {

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
	private List<Book> books;

	public Author() {
		super();
	}

	public Author(String name) {
		super();
		this.name = name;
	}

	public Author(String name, List<Book> books) {
		super();
		this.name = name;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	

}
