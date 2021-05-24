package com.example.demo.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonMessage;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.FullName;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping("/{id}/orders")
	public ResponseEntity<JsonMessage<List<OrderDto>>> findAllOrders(
			@PathVariable(name = "id", required = true) long id, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		List<OrderDto> result = service.getOrdersByUserId(id, pageable);
		return new ResponseEntity<JsonMessage<List<OrderDto>>>(
				new JsonMessage<List<OrderDto>>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}
	
	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<JsonMessage<List<UserDto>>> findAllUsers(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("X-Total-Count", String.valueOf(service.getTotal()));
		responseHeaders.add("X-Page-Number", String.valueOf(page));
		responseHeaders.add("X-Page-Size", String.valueOf(limit));
		Pageable pageable = PageRequest.of(page, limit);
		List<UserDto> result = service.findAll(pageable);
		return new ResponseEntity<JsonMessage<List<UserDto>>>(
				new JsonMessage<List<UserDto>>(Constants.StatusCode.OK.getValue(), result), responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<JsonMessage<UserDto>> getOne(@PathVariable long id) {
		UserDto result = service.findById(id);
		return new ResponseEntity<JsonMessage<UserDto>>(
				new JsonMessage<UserDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<JsonMessage<UserDto>> updateOne(@RequestBody UserDto dto, @PathVariable long id) {
		dto.setId(id);
		UserDto newUser = service.updateOne(dto);
		return new ResponseEntity<JsonMessage<UserDto>>(
				new JsonMessage<UserDto>(Constants.StatusCode.OK.getValue(), newUser), HttpStatus.OK);
	}
	
	
}
