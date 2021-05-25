package com.example.demo.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<JsonMessage<String>> handle(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		if (ex instanceof NullPointerException) {
			return new ResponseEntity<JsonMessage<String>>(
					new JsonMessage<String>(Constants.StatusCode.BAD_REQUEST.getValue()), HttpStatus.OK);
		} else if (ex instanceof org.springframework.security.access.AccessDeniedException) {
			return new ResponseEntity<JsonMessage<String>>(
					new JsonMessage<String>(Constants.StatusCode.FORBIDDEN.getValue()), HttpStatus.OK);
		}
		return new ResponseEntity<JsonMessage<String>>(
				new JsonMessage<String>(Constants.StatusCode.INTERNAL_ERROR.getValue()), HttpStatus.OK);
	}
}
