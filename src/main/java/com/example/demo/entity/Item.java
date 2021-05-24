package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_item")
public class Item extends BaseEntity {

	@Column(name = "type") // book: 1, clothes: 2, electric: 3
	private Integer type;

	@Column(name = "name") // 1, 2, 3
	private String name;

//	@Column(name = "slug")
//	private String slug;

	@Column(name = "description") // 1, 2, 3
	private String description;

	@Column(name = "price") // 1, 2, 3
	private Double price;
	
	@Column(name = "in_stock")
	private int inStock;
	
	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<ItemCart> itemCarts;
	
//	@Column(name = "publishing_year")
//	private Integer publishingYear; // 1

//	@Column(name = "number_of_pages")
//	private Integer numberOfPages; // 1

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "brand_id")
//	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subcategory_id") // 1, 2, 3
	private SubCategory subcategory;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id") // 1, 2, 3
	private Category category;

//	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private ProductStock stock = new ProductStock();

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private List<Image> images; // 1, 2, 3

//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	private List<Comment> comments;



//	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Clothes clothes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Book book;

//	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Electric electrics;

	public String getName() {
		return name;
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



	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}



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

//	public Integer getPublishingYear() {
//		return publishingYear;
//	}
//
//	public void setPublishingYear(Integer publishingYear) {
//		this.publishingYear = publishingYear;
//	}
//
//	public Integer getNumberOfPages() {
//		return numberOfPages;
//	}
//
//	public void setNumberOfPages(Integer numberOfPages) {
//		this.numberOfPages = numberOfPages;
//	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

//	public Brand getBrand() {
//		return brand;
//	}
//
//	public void setBrand(Brand brand) {
//		this.brand = brand;
//	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	public Set<Author> getAuthors() {
//		return authors;
//	}
//
//	public void setAuthors(Set<Author> authors) {
//		this.authors = authors;
//	}
//
//	public Publisher getPublisher() {
//		return publisher;
//	}
//
//	public void setPublisher(Publisher publisher) {
//		this.publisher = publisher;
//	}

//	public List<Comment> getComments() {
//		return comments;
//	}
//
//	public void setComments(List<Comment> comments) {
//		this.comments = comments;
//	}



	public List<ItemCart> getItemCarts() {
		return itemCarts;
	}



	public void setItemCarts(List<ItemCart> itemCarts) {
		this.itemCarts = itemCarts;
	}
	
	

}
