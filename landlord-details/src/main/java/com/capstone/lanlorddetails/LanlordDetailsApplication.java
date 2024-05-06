package com.capstone.lanlorddetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LanlordDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanlordDetailsApplication.class, args);
	}

}
