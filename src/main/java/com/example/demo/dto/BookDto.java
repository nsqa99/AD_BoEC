package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;

public class BookDto extends AbstractDTO<BookDto> {

	private int year;
	private int number;

	private Set<String> authors;
	private String publisher;

	public BookDto() {
		// TODO Auto-generated constructor stub
	}

	public BookDto(Book entity) {
		super();
		this.setId(entity.getId());
		this.year = entity.getYear();
		this.number = entity.getNumber();
		authors = new HashSet<>();
//		for (Author author : entity.getAuthors()) {
//			AuthorDto dto = new AuthorDto(author);
//			authors.add(dto.getName());
//		}
//		this.publisher = entity.getPublisher().getName();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Set<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

//	public Set<String> getPublishers() {
//		return publishers;
//	}
//
//	public void setPublishers(Set<String> publishers) {
//		this.publishers = publishers;
//	}

}
