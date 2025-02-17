package com.pn.food_cart_management.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;

	private String name;
    
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;
	
	private long price;

	private String category;
}
