package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;

@Service
public interface CategoryService {
	public List<CategoryDto> getAllCategoryWithSub();
	public List<CategoryDto> getAllCategory(String category);
	public CategoryDto saveOrUpdate(CategoryDto dto);
	public Boolean deleteCategory(Long id);
}
