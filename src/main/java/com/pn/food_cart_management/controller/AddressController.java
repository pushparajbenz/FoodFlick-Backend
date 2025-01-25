package com.pn.food_cart_management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pn.food_cart_management.dto.AddressDTO;
import com.pn.food_cart_management.entity.UserInfo;
import com.pn.food_cart_management.service.AddressService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RequestMapping("address-rest")
@RestController
@Tag(name="Address Controller")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@PostMapping("/addAddress/{userId}")
	public ResponseEntity<UserInfo> get(@RequestBody @Valid List<AddressDTO> address, @PathVariable long userId) {
		logger.info("Adding address for user ID: {}", userId);
		
		UserInfo userInfo = addressService.addAdress(address, userId);
		logger.info("Address added successfully for user ID: {}", userId);
		return ResponseEntity.ok(userInfo);
	}
	
	@GetMapping("/getAddress/{userId}")
	public ResponseEntity<List<AddressDTO>> getAddressOfUser(@PathVariable long userId) {
		logger.info("Fetching addresses for user ID: {}", userId);
		List<AddressDTO> addresses = addressService.getAddressOfUser(userId);
		logger.info("Fetched addresses: {}", addresses);
		return ResponseEntity.ok(addresses);
	}
	
	@DeleteMapping("/deleteAddress/{addressId}/{userId}")
	public ResponseEntity<String>deleteAddress(@PathVariable long addressId, @PathVariable long userId) {
		logger.info("Deleting address with ID: {} for user ID: {}", addressId, userId);
		addressService.deleteAddress(addressId, userId);
		logger.info("Address deleted successfully with ID: {} for user ID: {}", addressId, userId);
		
		return ResponseEntity.ok("Address deleted");
	}
}
