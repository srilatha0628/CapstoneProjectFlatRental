package com.capstone.flatbooking.service;


import java.util.List;

import com.capstone.flatbooking.entity.FlatBooking;

public interface FlatBookingService {

	FlatBooking placeAFlatBooking(FlatBooking flatBooking);
    
	FlatBooking viewBookingById(int bookingId);
    
    List<FlatBooking> viewAllBookings();
    
    void deleteBooking(int bookingId);
     
	void acceptBooking(int bookingId);

    void denyBooking(int bookingId);
    
    String viewBookingStatusbyId(int bookingId);

	FlatBooking getBookingDetailsById(int bookingId);
	
}
