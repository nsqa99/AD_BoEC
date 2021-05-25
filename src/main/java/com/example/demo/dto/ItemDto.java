package com.example.demo.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Author;
import com.example.demo.entity.Image;
import com.example.demo.entity.Item;

public class ItemDto extends AbstractDTO<ItemDto> {
	private Integer type;
	private String typeName;
	private String name;
//	private String slug;
	private String description;
	private Double price;
	private List<String> images;
	private String categoryCode;
	private String subcategoryCode;
	private Integer publishingYear;
	private Integer numberOfPages;
	private Set<String> authors;
	private String publisher;
	private int inStock;

	public ItemDto() {
		super();
	}

	public ItemDto(Item entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();

		if (this.type != null) {
			switch (this.type) {
			case 1:
				this.typeName = "Sách";
				break;
			case 2:
				this.typeName = "Quần áo";
				break;
			case 3:
				this.typeName = "Đồ điện tử";
				break;

			default:
				break;
			}
		}

		this.name = entity.getName();
//		this.slug = entity.getSlug();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.categoryCode = entity.getCategory().getCode();
		this.subcategoryCode = entity.getSubcategory().getCode();
		this.inStock = entity.getInStock();
		images = new ArrayList<>();
		if (entity.getImages() != null) {
			for (Image image : entity.getImages()) {
				ImageDto dto = new ImageDto(image);
				images.add(dto.getUrl());
			}
		}
		

		if (this.categoryCode.equalsIgnoreCase("sach")) {
			this.publishingYear = entity.getBook().getYear();
			this.numberOfPages = entity.getBook().getNumber();
			authors = new HashSet<>();
			for (Author author : entity.getBook().getAuthors()) {
				AuthorDto dto = new AuthorDto(author);
				authors.add(dto.getName());
			}
			this.publisher = entity.getBook().getPublisher().getName();
		}
	}

	
	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getSlug() {
//		return slug;
//	}
//
//	public void setSlug(String slug) {
//		this.slug = slug;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSubcategoryCode() {
		return subcategoryCode;
	}

	public void setSubcategoryCode(String subcategoryCode) {
		this.subcategoryCode = subcategoryCode;
	}

	public Integer getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(Integer publishingYear) {
		this.publishingYear = publishingYear;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
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

}
