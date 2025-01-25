package com.pn.food_cart_management.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
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
public class AddressDTO {
	
	private String street;
	
	@NotEmpty(message = "Please provide city")
	private String city;
	
	@NotEmpty(message = "Please provide the state")
	private String state;
	
	@NotNull(message="ZipCode can't be null")
	@Column(name="zip_code")
	private String zipCode;
}
