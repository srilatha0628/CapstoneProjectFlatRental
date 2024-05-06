package com.capstone.tenantdetails.service;

	import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.tenantdetails.model.FlatBooking;

	@FeignClient(name = "FLATBOOKING-DETAILS")
	public interface FlatBookingConsumer {
		
		@PostMapping("/flatbooking/requestbooking")
		FlatBooking addBooking(@RequestBody FlatBooking flatBooking);
		
		@GetMapping("/flatbooking/status/{id}")
		String viewBookingStatus(@PathVariable("id") int bookingId)	;

	}

