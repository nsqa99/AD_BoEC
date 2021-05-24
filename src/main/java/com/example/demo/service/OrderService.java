package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDto;

@Service
public interface OrderService {
	public List<OrderDto> findAll(Pageable pageable);
	public OrderDto findById(long id);
	public List<OrderDto> findAllByUserId(long id, Pageable pageable);
	public OrderDto insert(OrderDto order) throws Exception;
	public OrderDto update(long id);
	public void delete(long id);
}
