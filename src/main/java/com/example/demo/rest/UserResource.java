package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonMessage;
import com.example.demo.dto.OrderDto;
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
}
