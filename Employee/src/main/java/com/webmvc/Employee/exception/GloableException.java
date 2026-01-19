package com.webmvc.Employee.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloableException {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>>exceptionHandling(MethodArgumentNotValidException e){
		
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		Map<String, String>errors = new HashMap<>();
		for(FieldError error : fieldErrors  )
			errors.put(error.getField(), error.getDefaultMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MyExceptionHandler>exceptionHandler(IllegalArgumentException e){
		
		MyExceptionHandler error = MyExceptionHandler.builder()
	            .statusCode(HttpStatus.NOT_FOUND.value())
	            .message(e.getMessage())
	            .timeStemp(String.valueOf(System.currentTimeMillis()))
	            .build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MyExceptionHandler>exceptionHandler(Exception e){
		new MyExceptionHandler();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyExceptionHandler.builder().statusCode(405).message(e.getMessage()).timeStemp(String.valueOf(System.currentTimeMillis())).build());
	}
}
