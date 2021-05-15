package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SubCategoryDto;
import com.example.demo.service.SubCategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/subcategory")
public class RestSubCategoryController {

	@Autowired
	private SubCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<List<SubCategoryDto>> getAll() {
		List<SubCategoryDto> result = service.getAllSubCategory();
		return new ResponseEntity<List<SubCategoryDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{category}")
	public ResponseEntity<List<SubCategoryDto>> getAll(@PathVariable String category) {
		List<SubCategoryDto> result = service.getSubCategoryByCategory(category);
		return new ResponseEntity<List<SubCategoryDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<SubCategoryDto> create(@RequestBody SubCategoryDto dto) {
		SubCategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SubCategoryDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<SubCategoryDto> update(@RequestBody SubCategoryDto dto, @PathVariable Long id) {
		dto.setId(id);
		SubCategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SubCategoryDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.deleteSubCategory(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
}
