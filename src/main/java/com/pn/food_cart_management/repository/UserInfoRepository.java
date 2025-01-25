package com.pn.food_cart_management.repository; 

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pn.food_cart_management.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByName(String username);
	Optional<UserInfo> findByEmailId(String email);
	List<UserInfo> findAllById(long userId);
}
