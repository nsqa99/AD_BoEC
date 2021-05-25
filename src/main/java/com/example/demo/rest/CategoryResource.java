package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonMessage;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.SubCategoryDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.SubCategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/category")
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@Autowired
	private SubCategoryService subService;
	
	@GetMapping("")
	public ResponseEntity<JsonMessage<List<CategoryDto>>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("X-Total-Count", String.valueOf(service.getTotal()));
		responseHeaders.add("X-Page-Number", String.valueOf(page));
		responseHeaders.add("X-Page-Size", String.valueOf(limit));
		Pageable pageable = PageRequest.of(page, limit);
		List<CategoryDto> result = service.findAll(pageable);
		return new ResponseEntity<JsonMessage<List<CategoryDto>>>(
				new JsonMessage<List<CategoryDto>>(Constants.StatusCode.OK.getValue(), result),
				responseHeaders,
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/subcategory")
	public ResponseEntity<JsonMessage<List<SubCategoryDto>>> getSubByCateId(
			@PathVariable Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("X-Total-Count", String.valueOf(service.getTotalSubCate(id)));
		responseHeaders.add("X-Page-Number", String.valueOf(page));
		responseHeaders.add("X-Page-Size", String.valueOf(limit));
		CategoryDto cate = service.getById(id);
		List<SubCategoryDto> result = subService.getSubCategoryByCategory(cate.getCode());
		return new ResponseEntity<JsonMessage<List<SubCategoryDto>>>(
				new JsonMessage<List<SubCategoryDto>>(Constants.StatusCode.OK.getValue(), result),
				responseHeaders,
				HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<JsonMessage<CategoryDto>> create(@RequestBody CategoryDto dto) {
		CategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<JsonMessage<CategoryDto>>(
				new JsonMessage<CategoryDto>(Constants.StatusCode.OK.getValue(), result), 
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<JsonMessage<CategoryDto>> update(@RequestBody CategoryDto dto, @PathVariable Long id) {
		dto.setId(id);
		CategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<JsonMessage<CategoryDto>>(
				new JsonMessage<CategoryDto>(Constants.StatusCode.OK.getValue(), result), 
				HttpStatus.OK);
	}
	
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<JsonMessage<Boolean>> delete(@PathVariable Long id) {
		Boolean result = service.deleteCategory(id);
		return new ResponseEntity<JsonMessage<Boolean>>(new JsonMessage<Boolean>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

}
