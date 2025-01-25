package com.pn.food_cart_management.payload;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	
	private String message;
	private LocalDate date;
	private HttpStatus status;

}