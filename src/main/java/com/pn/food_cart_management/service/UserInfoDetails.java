package com.pn.food_cart_management.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pn.food_cart_management.entity.UserInfo;

public class UserInfoDetails implements UserDetails {

	
	private String name;
	private String password;
	private GrantedAuthority authorities;

	public UserInfoDetails(UserInfo userInfo) {
		name = userInfo.getEmailId();
		password = userInfo.getPassword();
		authorities = new SimpleGrantedAuthority(userInfo.getRole());
	}

	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return List.of(authorities);
	}
}
