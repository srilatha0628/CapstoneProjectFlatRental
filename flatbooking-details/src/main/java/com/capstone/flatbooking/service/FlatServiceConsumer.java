package com.capstone.flatbooking.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.flatbooking.model.Flat;

@FeignClient(name="FLAT-DETAILS")
public interface FlatServiceConsumer {
	
	@GetMapping("/flatdetails/{id}")
	Flat getFlatById(@PathVariable("id") int flatNo);
	
}
