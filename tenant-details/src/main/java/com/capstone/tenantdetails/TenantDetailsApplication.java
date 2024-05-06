package com.capstone.tenantdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients 
@SpringBootApplication
public class TenantDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantDetailsApplication.class, args);
	}

}
