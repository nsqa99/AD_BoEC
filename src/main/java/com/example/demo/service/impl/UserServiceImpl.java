package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderService orderService;

	@Override
	public UserDto getCurrentUser(Long id) {
		User user = userRepository.getOne(id);
		UserDto dto = new UserDto(user);
		return dto;
	}

	@Override
	public List<OrderDto> getOrdersByUserId(long id, Pageable pageable) {
		List<OrderDto> result = orderService.findAllByUserId(id, pageable);
		return result;
	}
	
}
