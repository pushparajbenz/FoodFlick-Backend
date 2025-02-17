package com.pn.food_cart_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfig {
	  @Bean
	    WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry corsRegistry) {
	                corsRegistry.addMapping("/**")
	                    .allowedOrigins("http://localhost:4200")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE")
	                    .allowedHeaders("*");
	            }
	        };
	    }

}
