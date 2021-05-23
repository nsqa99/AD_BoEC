package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentDto;

@Service
public interface PaymentService {
	public List<PaymentDto> findAll(Pageable pageable);
	public PaymentDto getById(long id);
	public PaymentDto getByUserId(long id);
	public void insert(PaymentDto payment);
	public void update(long id);
	public void delete(long id);
}
