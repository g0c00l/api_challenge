package com.disney.studios.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcpetionHandler {

	@ExceptionHandler(DisneyException.class)
	public ResponseEntity<String> handleException(DisneyException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMsg());
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

}
