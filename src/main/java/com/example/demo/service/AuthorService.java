package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthorDto;

@Service
public interface AuthorService {
	public List<AuthorDto> getAll();

	public AuthorDto saveOrUpdate(AuthorDto dto);

	public Boolean delete(Long id);
}
