package com.capstone.tenantdetails.service;

	import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.capstone.tenantdetails.model.Flat;

	@FeignClient(name="FLAT-DETAILS")
	public interface FlatServiceConsumer {
		
		@GetMapping("/flatdetails/all")
		List<Flat> viewAllAvailableFlats();

	}


