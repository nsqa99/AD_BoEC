package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonMessage;
import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/orders")
public class OrderResources {
	@Autowired
	private OrderService service;

	@PostMapping("")
	public ResponseEntity<JsonMessage<OrderDto>> create(@RequestBody OrderDto dto) {
		OrderDto result = service.insert(dto);
		if (result == null)
			return new ResponseEntity<JsonMessage<OrderDto>>(
					new JsonMessage<OrderDto>(Constants.StatusCode.CREATE_ERROR.getValue(), result), HttpStatus.OK);
		return new ResponseEntity<JsonMessage<OrderDto>>(
				new JsonMessage<OrderDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}
}
