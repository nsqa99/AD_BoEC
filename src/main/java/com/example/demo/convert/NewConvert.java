package com.example.demo.convert;

import org.springframework.stereotype.Component;

import com.example.demo.dto.NewsDto;
import com.example.demo.entity.NewsEntity;

@Component
public class NewConvert {

	public NewsEntity toEntity(NewsDto dto) {
		NewsEntity entity = new NewsEntity();
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setThumbnail(dto.getThumbnail());
		entity.setShortDescription(dto.getShortDescription());
		return entity;
	}

	public NewsDto toDTO(NewsEntity entity) {
		NewsDto dto = new NewsDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setThumbnail(entity.getThumbnail());
		dto.setShortDescription(entity.getShortDescription());
		dto.setCategoryCode(entity.getCategory().getCode());
//		dto.setCreatedBy(entity.getCreatedBy());
//		dto.setCreatedDate(entity.getCreatedDate());
//		dto.setModifiedBy(entity.getModifiedBy());
//		dto.setModifiedDate(entity.getModifiedDate());
		
		return dto;
	}
	
	public NewsEntity toEntity(NewsDto dto, NewsEntity entity) {
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumbnail(dto.getThumbnail());
		return entity;
	}

}
