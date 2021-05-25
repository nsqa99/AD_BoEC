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
import com.example.demo.dto.SubCategoryDto;
import com.example.demo.service.SubCategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/subcategory")
public class SubCategoryResource {

	@Autowired
	private SubCategoryService service;
	
	@GetMapping("")
	public ResponseEntity<JsonMessage<List<SubCategoryDto>>> getAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("X-Total-Count", String.valueOf(service.getTotal()));
		responseHeaders.add("X-Page-Number", String.valueOf(page));
		responseHeaders.add("X-Page-Size", String.valueOf(limit));
		Pageable pageable = PageRequest.of(page, limit);
		List<SubCategoryDto> result = service.getAllSubCategory();
		return new ResponseEntity<JsonMessage<List<SubCategoryDto>>>(
				new JsonMessage<List<SubCategoryDto>>(Constants.StatusCode.OK.getValue(), result),
				responseHeaders,
				HttpStatus.OK);
	}
	
//	@GetMapping("/{category}")
//	public ResponseEntity<List<SubCategoryDto>> getAll(@PathVariable String category) {
//		List<SubCategoryDto> result = service.getSubCategoryByCategory(category);
//		return new ResponseEntity<List<SubCategoryDto>>(result, HttpStatus.OK);
//	}

	@PostMapping("")
	public ResponseEntity<JsonMessage<SubCategoryDto>> create(@RequestBody SubCategoryDto dto) {
		SubCategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<JsonMessage<SubCategoryDto>>(
				new JsonMessage<SubCategoryDto>(Constants.StatusCode.OK.getValue(), result),
				HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<JsonMessage<SubCategoryDto>> update(@RequestBody SubCategoryDto dto, @PathVariable Long id) {
		dto.setId(id);
		SubCategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<JsonMessage<SubCategoryDto>>(
				new JsonMessage<SubCategoryDto>(Constants.StatusCode.OK.getValue(), result),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<JsonMessage<Boolean>> delete(@PathVariable Long id) {
		Boolean result = service.deleteSubCategory(id);
		return new ResponseEntity<JsonMessage<Boolean>>(
				new JsonMessage<Boolean>(Constants.StatusCode.OK.getValue(), result),
				HttpStatus.OK);
	}
	
}
