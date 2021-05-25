package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;

@Service
public interface CategoryService {
	public List<CategoryDto> findAll(Pageable pageable);
	public List<CategoryDto> getAllCategory(String category);
	public CategoryDto getById(long id);
	public CategoryDto saveOrUpdate(CategoryDto dto);
	public Boolean deleteCategory(Long id);
	public long getTotal();
	public long getTotalSubCate(long id);
}
