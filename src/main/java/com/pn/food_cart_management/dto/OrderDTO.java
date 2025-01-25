package com.pn.food_cart_management.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pn.food_cart_management.utils.OrderDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
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
public class OrderDTO {
	
	@Column(name="user_id",nullable = false)
	@NotNull
	private long userId;
	
	@Column(name="summary",nullable = false,columnDefinition = "TEXT")
	@NotEmpty
	private String summary;
	
	@Column(name="address",nullable = false)
	@NotEmpty
	private String address;
	
	@Temporal(TemporalType.DATE)
	private Date calendarDate;
	
	@Column(name="price",nullable = false)
	@NotNull
	private long price;
	
	@Transient
	private List<OrderDetails> description=new ArrayList<>();
}
