package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthorDto;
import com.example.demo.service.AuthorService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/author")
public class RestAuthorController {
	
	@Autowired
	private AuthorService service;
	
	@GetMapping("")
	public ResponseEntity<List<AuthorDto>> getAll() {
		List<AuthorDto> result = service.getAll();
		return new ResponseEntity<List<AuthorDto>>(result, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto dto) {
		AuthorDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<AuthorDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AuthorDto> update(@RequestBody AuthorDto dto, @PathVariable Long id) {
		dto.setId(id);
		AuthorDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<AuthorDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
