package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.service.CommentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/comment")
public class RestCommentController {

	@Autowired
	private CommentService service;

	@PostMapping("")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto) {
		CommentDto result = service.createComment(dto);
		return new ResponseEntity<CommentDto>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<Page<CommentDto>> getAllByProduct(@RequestParam(name = "product") Long productId,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		SearchDto dto = new SearchDto(page, limit, productId);
		Page<CommentDto> result = service.getAllCommentByProduct(dto);
		return new ResponseEntity<Page<CommentDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<CommentDto>> getAllByUser(@RequestParam(name = "username") String username) {
		List<CommentDto>result = service.getAllCommentByUser(username);
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}

}
