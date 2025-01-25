package com.pn.food_cart_management.dto;


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
public class UserInfoDTO {

	private String name;
	private String emailId;
	private String password;
	private String role;	
}
