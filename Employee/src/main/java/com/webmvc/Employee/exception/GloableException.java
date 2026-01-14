package com.webmvc.Employee.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloableException {

	
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ResponseEntity<MyExceptionHandler>exceptionHandler(Exception e){
//		new MyExceptionHandler();
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyExceptionHandler.builder().statusCode(405).error(e.getMessage()).timeStemp(LocalDateTime.now()).build());
//	}
}
