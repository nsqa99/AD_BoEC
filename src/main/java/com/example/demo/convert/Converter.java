package com.example.demo.convert;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class Converter {
	public static  <T> Page<T> toPage(List<T> list, Pageable pageable) {
		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		int total = list.size() / pageSize;
		
		int max = pageNumber >= total ? list.size() : pageSize*(pageNumber+1);
        int min = pageNumber > total ? max : pageSize*pageNumber;
        
        
        Page<T> pageResponse = new PageImpl<T>(list.subList(min, max), pageable,
                list.size());
        return pageResponse;
	}
}
