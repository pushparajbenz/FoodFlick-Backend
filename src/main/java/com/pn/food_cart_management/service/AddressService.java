package com.pn.food_cart_management.service;

import java.util.List;

import com.pn.food_cart_management.dto.AddressDTO;
import com.pn.food_cart_management.entity.UserInfo;

public interface AddressService {
	
	public UserInfo addAdress(List<AddressDTO> addressDto, long userId);
	public List<AddressDTO> getAddressOfUser(long userId);
	void deleteAddress(long addressId, long userId);

}
