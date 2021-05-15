package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewsDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.service.NewsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/new")
public class RestNewsController {

	@Autowired
	private NewsService newService;
	
	@GetMapping(value = "/user")
	@PreAuthorize("hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<NewsDto>> getPage(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		
		Pageable pageable = PageRequest.of(page-1, limit);
		List<NewsDto> result = newService.getPage(pageable, keyword);
		return new ResponseEntity<List<NewsDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<Page<NewsDto>> searchByPage(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword, null, null);
		Page<NewsDto> result = newService.searchByPage(dto);
		return new ResponseEntity<Page<NewsDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/danh-muc/{category}")
	public ResponseEntity<Page<NewsDto>> searchByPageCategory(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit, @PathVariable String category) {
		SearchDto dto = new SearchDto(page, limit, null, category, null);
		Page<NewsDto> result = newService.searchByPage(dto);
		return new ResponseEntity<Page<NewsDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<NewsDto> getNewById(@PathVariable Long id) {
		NewsDto result = newService.getNewById(id);
		return new ResponseEntity<NewsDto>(result, HttpStatus.OK);
	}

	@PostMapping(value = "")
	public ResponseEntity<NewsDto> createNew(@RequestBody NewsDto newDto) {
		NewsDto result = newService.saveOrUpdate(newDto);
		return new ResponseEntity<NewsDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<NewsDto> save(@RequestBody NewsDto dto, @PathVariable Long id) {
		dto.setId(id);
		NewsDto newDto = newService.saveOrUpdate(dto);
		return new ResponseEntity<NewsDto>(newDto, HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Boolean> deleteNew(@PathVariable Long id) {
		Boolean result = newService.deleteNew(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
