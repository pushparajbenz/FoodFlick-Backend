package com.pn.food_cart_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pn.food_cart_management.filter.JwtAuthFilter;
import com.pn.food_cart_management.service.UserInfoService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;
	
	
	
	// User Creation
	@Bean
      public UserDetailsService userDetailsService() {
		return new UserInfoService();
	}

	// Configuring HttpSecurity
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				(requests) -> requests
				.requestMatchers("/auth/addNewUser", "/auth/generateToken","/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
						"/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
						"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html")
				.permitAll()
				.requestMatchers("/auth/admin/userdetails", "/product-rest/admin/product/**","/order-rest/admin/**").hasRole("ADMIN")
				.requestMatchers("/cart-rest/**","/auth/user/profile","/address-rest/**","/product-rest/user/**","/order-rest/user/**").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider())
 				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.csrf(csrf -> csrf.disable());
				
		return http.build();

	}

	// Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	//define the bean for authentication manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
