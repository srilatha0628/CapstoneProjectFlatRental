package com.capstone.flatbooking;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FlatbookingDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlatbookingDetailsApplication.class, args);
	}

}
