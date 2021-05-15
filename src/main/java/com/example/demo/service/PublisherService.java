package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PublisherDto;

@Service
public interface PublisherService {
	public List<PublisherDto> getAll();

	public PublisherDto saveOrUpdate(PublisherDto dto);

	public Boolean delete(Long id);
}
