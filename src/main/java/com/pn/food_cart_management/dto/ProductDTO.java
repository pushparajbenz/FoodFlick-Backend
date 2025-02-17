package com.pn.food_cart_management.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
	
	private String name;

	private MultipartFile image;
	
	@NotNull
	private long price;
	
	private String Category;

}
