package com.example.demo.dto;

import com.example.demo.entity.Comment;

public class CommentDto extends AbstractDTO<CommentDto> {

	private String content;
	private Float rating;
	private String username;
	private Long productId;
	private String displayName;

	public CommentDto() {
		super();
	}

	public CommentDto(Comment entity) {
		this.setId(entity.getId());
		this.content = entity.getContent();
		this.rating = entity.getRating();
		this.displayName = entity.getDisplayName();
		this.username = entity.getUser().getUsername();
		this.productId = entity.getProduct().getId();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
