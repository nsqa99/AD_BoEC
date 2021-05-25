package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemDto;
import com.example.demo.dto.SearchDto;

@Service
public interface ItemService {
	
	public List<ItemDto> findAll(Pageable pageable);

	public Page<ItemDto> searchByPage(SearchDto dto);

	public ItemDto getProductById(Long id);

	public ItemDto saveOrUpdate(ItemDto dto);
	
	public ItemDto insert(ItemDto dto);
	
	public ItemDto update(ItemDto dto);

	public Boolean delete(Long id);

	public long getTotal();

}
