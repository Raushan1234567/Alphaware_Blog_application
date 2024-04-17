package com.alphabare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(UserAllreadyRegisteredException.class)
	public ResponseEntity<String> userAllreadyExists(UserAllreadyRegisteredException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> GeneralExceptionHandler(Exception se){
		return new ResponseEntity<>(se.getMessage() , HttpStatus.BAD_REQUEST) ;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> NoHandelerExceptionHandler(NoHandlerFoundException se){
		return new ResponseEntity<>("There is no handler for this endpoint" , HttpStatus.BAD_REQUEST) ;
	}
}
