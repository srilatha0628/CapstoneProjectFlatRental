package com.capstone.lanlorddetails.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.capstone.lanlorddetails.model.FlatBooking;

@FeignClient(name = "FLATBOOKING-DETAILS")
public interface FlatBookingConsumer {
    
    @PostMapping("/flatbooking/acceptbooking/{id}")
    FlatBooking acceptBooking(@PathVariable("id") int bookingId);
    
    @PostMapping("/flatbooking/denybooking/{id}")
    FlatBooking denyBooking(@PathVariable("id") int bookingId);
    
}
