package com.example.demo.rest;

import java.util.List;
import com.example.demo.common.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.example.demo.common.JsonMessage;
import com.example.demo.dto.ItemDto;
import com.example.demo.service.ItemService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/items")
public class ItemResources {

	@Autowired
	private ItemService service;

	@GetMapping("")
	public ResponseEntity<JsonMessage<List<ItemDto>>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		List<ItemDto> result = service.findAll(pageable);
		return new ResponseEntity<JsonMessage<List<ItemDto>>>(
				new JsonMessage<List<ItemDto>>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

//	@GetMapping(value = "/search")
//	public ResponseEntity<Page<ItemDto>> searchByPage(@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "limit", defaultValue = "10") int limit,
//			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
//		SearchDto dto = new SearchDto(page, limit, keyword, null, null);
//		Page<ItemDto> result = service.searchByPage(dto);
//		return new ResponseEntity<Page<ItemDto>>(result, HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/category{category}", name = "getByCategory")
//	public ResponseEntity<Page<ItemDto>> searchByPageCategory(
//			@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "limit", defaultValue = "10") int limit,
//			@RequestParam(name = "keyword", defaultValue = "") String keyword, @PathVariable String category) {
//		SearchDto dto = new SearchDto(page, limit, keyword, category, null);
//		Page<ItemDto> result = service.searchByPage(dto);
//		return new ResponseEntity<Page<ItemDto>>(result, HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/category/{category}/{subcategory}")
//	public ResponseEntity<Page<ItemDto>> searchByPageSubCategory(
//			@RequestParam(name = "page", defaultValue = "0") int page,
//			@RequestParam(name = "limit", defaultValue = "10") int limit,
//			@RequestParam(name = "keyword", defaultValue = "") String keyword, @PathVariable String category,
//			@PathVariable String subcategory) {
//		SearchDto dto = new SearchDto(page, limit, keyword, category, subcategory);
//		Page<ItemDto> result = service.searchByPage(dto);
//		return new ResponseEntity<Page<ItemDto>>(result, HttpStatus.OK);
//	}

	@GetMapping(value = "/{id}", name = "getById")
	public ResponseEntity<JsonMessage<ItemDto>> getProductById(@PathVariable Long id) {
		ItemDto result = service.getProductById(id);
		return new ResponseEntity<JsonMessage<ItemDto>>(
				new JsonMessage<ItemDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<JsonMessage<ItemDto>> create(@RequestBody ItemDto dto) {
		ItemDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<JsonMessage<ItemDto>>(
				new JsonMessage<ItemDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<JsonMessage<ItemDto>> update(@RequestBody ItemDto dto, @PathVariable Long id) {
		dto.setId(id);
		ItemDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<JsonMessage<ItemDto>>(
				new JsonMessage<ItemDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<JsonMessage<Boolean>> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<JsonMessage<Boolean>>(
				new JsonMessage<Boolean>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

}
