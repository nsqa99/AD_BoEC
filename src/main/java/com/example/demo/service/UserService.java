package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;

@Service
public interface UserService {
	public UserDto getCurrentUser(Long id);
	public List<OrderDto> getOrdersByUserId(long id, Pageable pageable);
	public UserDto updateOne(UserDto dto);
	public List<UserDto> findAll(Pageable pageable);
	public UserDto findById(long id);
	public long getTotal();
}
