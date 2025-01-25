package com.pn.food_cart_management.exception;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pn.food_cart_management.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=UserAlreadyExistsException.class)
	public ResponseEntity<ApiResponse> UserAlreadyExistsException(UserAlreadyExistsException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.FORBIDDEN);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}

	
	@ExceptionHandler(value=UserNotExistException.class)
	public ResponseEntity<ApiResponse> UserNotExistException(UserNotExistException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.FORBIDDEN);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse> accessunauthorized(AccessDeniedException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.FORBIDDEN);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResponse> missingparam(MissingServletRequestParameterException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.FORBIDDEN);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ApiResponse> orderNotFound(OrderNotFoundException ex)
	{
		String message=ex.getMessage();
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
		LocalDate localDate = zonedDateTime.toLocalDate();

		ApiResponse apiResponse=new ApiResponse(message,localDate,HttpStatus.FORBIDDEN);
	     return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.FORBIDDEN);
	}


}
