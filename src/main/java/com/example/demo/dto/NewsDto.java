package com.example.demo.dto;

import com.example.demo.entity.NewsEntity;

public class NewsDto extends AbstractDTO<NewsDto> {
	private String title;
	private String content;
	private String shortDescription;
	private String categoryCode;
	private String thumbnail;

	public NewsDto() {
		
	}
	

	public NewsDto(NewsEntity entity) {
		super();
		this.setId(entity.getId());
//		this.setCreatedDate(entity.getCreatedDate());
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.shortDescription = entity.getShortDescription();
		this.categoryCode = entity.getCategory().getCode();
		this.thumbnail = entity.getThumbnail();
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

}
