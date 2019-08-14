package com.poly.toba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
			.allowedOrigins("http://15.164.160.236:80").allowedMethods("GET", "POST","PUT", "DELETE");
		registry.addMapping("/**").allowCredentials(true);
	}

}
