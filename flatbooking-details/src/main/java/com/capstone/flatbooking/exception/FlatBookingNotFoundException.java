package com.capstone.flatbooking.exception;


public class FlatBookingNotFoundException extends RuntimeException {
	
	public FlatBookingNotFoundException() {
		
	}
	
	public FlatBookingNotFoundException(String msg) {
		super(msg);
	}

}

