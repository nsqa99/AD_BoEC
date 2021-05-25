package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repos;

//	@Autowired
//	private SubCategoryRepository subRepos;

	@Override
	public List<CategoryDto> findAll(Pageable pageable) {
		return repos.findAll()
				.stream()
				.map(c -> new CategoryDto(c))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto saveOrUpdate(CategoryDto dto) {
		if (dto != null) {
			Category entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Category();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());

			entity = repos.save(entity);

			if (entity != null) {
				return new CategoryDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<CategoryDto> getAllCategory(String categoryCode) {
		return null;
	}

	@Override
	public CategoryDto getById(long id) {
		Category cate = repos.findById(id).orElse(null);
		if (cate != null) return new CategoryDto(cate);
		return null;
	}

	@Override
	public long getTotal() {
		return repos.count();
	}

	@Override
	public long getTotalSubCate(long id) {
		return repos.countSubCategory(id);
	}

}
