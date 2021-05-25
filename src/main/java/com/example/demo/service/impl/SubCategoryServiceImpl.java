package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SubCategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository repos;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<SubCategoryDto> getAllSubCategory() {
		List<SubCategoryDto> list = new ArrayList<>();
		List<SubCategory> entities = repos.findAll();
		for (SubCategory entity : entities) {
			SubCategoryDto dto = new SubCategoryDto(entity);
			list.add(dto);
		}

		return list;
	}

	@Override
	public List<SubCategoryDto> getSubCategoryByCategory(String categoryCode) {
		List<SubCategoryDto> list = new ArrayList<>();
		List<SubCategory> entities = repos.findAllByCategoryCode(categoryCode);
		for (SubCategory entity : entities) {
			SubCategoryDto dto = new SubCategoryDto(entity);
			list.add(dto);
		}

		return list;
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	public SubCategoryDto saveOrUpdate(SubCategoryDto dto) {
		
		Category category = categoryRepository.findOneByCode(dto.getCategoryCode());
		
		if (dto != null) {
			SubCategory entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new SubCategory();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCategory(category);

			entity = repos.save(entity);

			if (entity != null) {
				return new SubCategoryDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteSubCategory(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public long getTotal() {
		return repos.count();
	}

}
