package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SubCategoryDto;

@Service
public interface SubCategoryService {
	
	public List<SubCategoryDto> getAllSubCategory();
	
	public List<SubCategoryDto> getSubCategoryByCategory(String categoryCode);
	
	public SubCategoryDto saveOrUpdate(SubCategoryDto dto);

	public Boolean deleteSubCategory(Long id);
}
