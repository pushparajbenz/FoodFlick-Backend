package com.pn.food_cart_management.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.pn.food_cart_management.utils.OrderDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_table")
@Builder
@ToString
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	
	private long userId;
	
	private String summary;
	
	private String address;

	private Timestamp calendarDate;
	
	private long price;
	
	@Transient
	private List<OrderDetails> description=new ArrayList<>();

}
