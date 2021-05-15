package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.NewsDto;
import com.example.demo.dto.SearchDto;

@Service
public interface NewsService {
	public List<NewsDto> getPage(Pageable pageable, String keyword);
	
	public Page<NewsDto> searchByPage(SearchDto dto);

	public NewsDto saveOrUpdate(NewsDto newDto);
	
	public NewsDto getNewById(Long id);

	public Boolean deleteNew(Long id);
}
