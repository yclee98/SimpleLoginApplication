package com.example.simplelogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.simplelogin.Model.AuthenticationFilter;

@SpringBootApplication
public class SimpleloginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleloginApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<AuthenticationFilter>();
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		registrationBean.setFilter(authenticationFilter);
		registrationBean.addUrlPatterns("/api/users/allusers");
		return registrationBean;
	}
}
