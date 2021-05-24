package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.example.demo.dto.ItemDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/orders")
public class OrderResource {
	@Autowired
	private OrderService service;

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<JsonMessage<OrderDto>> create(@RequestBody OrderDto dto) {
		OrderDto result;
		try {
			result = service.insert(dto);
			if (result != null)
				return new ResponseEntity<JsonMessage<OrderDto>>(
						new JsonMessage<OrderDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JsonMessage<OrderDto>>(
					new JsonMessage<OrderDto>(Constants.StatusCode.BAD_REQUEST.getValue(), null), HttpStatus.OK);
		}

		return new ResponseEntity<JsonMessage<OrderDto>>(
				new JsonMessage<OrderDto>(Constants.StatusCode.CREATE_ERROR.getValue()), HttpStatus.OK);
	}

	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<JsonMessage<List<OrderDto>>> findAll(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		List<OrderDto> result = service.findAll(pageable);
		return new ResponseEntity<JsonMessage<List<OrderDto>>>(
				new JsonMessage<List<OrderDto>>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JsonMessage<OrderDto>> getOrderById(@PathVariable Long id) {
		OrderDto result = service.findById(id);
		return new ResponseEntity<JsonMessage<OrderDto>>(
				new JsonMessage<OrderDto>(Constants.StatusCode.OK.getValue(), result), HttpStatus.OK);
	}

}
