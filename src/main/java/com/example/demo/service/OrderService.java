package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDto;

@Service
public interface OrderService {
	public List<OrderDto> findAll(Pageable pageable);
	public OrderDto getById(long id);
	public OrderDto getByUserId(long id);
	public OrderDto insert(OrderDto order);
	public OrderDto update(long id);
	public void delete(long id);
}
