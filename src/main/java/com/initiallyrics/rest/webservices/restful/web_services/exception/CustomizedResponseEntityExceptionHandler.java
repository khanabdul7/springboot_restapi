package com.initiallyrics.rest.webservices.restful.web_services.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ResponseEntityExceptionHandler contains all method to handle spring MVC exceptions.
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class) //Exception.class means we want to handle all kind of exceptions
	public final ResponseEntity<ErrorHandling> handleAllException(Exception ex, WebRequest request){  

		ErrorHandling errorHandling = new ErrorHandling(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorHandling>(errorHandling, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class) //only USerNotFound Exceptions are handle by this method.
	public final ResponseEntity<ErrorHandling> handleUserNotFoundException(Exception ex, WebRequest request){  

		ErrorHandling errorHandling = new ErrorHandling(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorHandling>(errorHandling, HttpStatus.NOT_FOUND);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorHandling errorHandling = new ErrorHandling(LocalDateTime.now(), "Total Errors: "+ex.getErrorCount() + " First Error:  " +ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(errorHandling, HttpStatus.BAD_REQUEST);
		
	}
}
