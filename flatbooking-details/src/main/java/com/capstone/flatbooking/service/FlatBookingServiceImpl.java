package com.capstone.flatbooking.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.flatbooking.entity.FlatBooking;
import com.capstone.flatbooking.exception.FlatBookingNotFoundException;
import com.capstone.flatbooking.repository.FlatBookingRepository;

@Service
public class FlatBookingServiceImpl implements FlatBookingService {

    @Autowired
    private FlatBookingRepository flatBookingRepository;

    @Override
    public FlatBooking placeAFlatBooking(FlatBooking flatBooking) {
        flatBooking.setBookingDate(LocalDate.now());
        flatBooking.setBookingStatus("pending");
        flatBooking.setBookingFromDate(LocalDate.of(2024, 05, 06));
        flatBooking.setBookingToDate(LocalDate.of(2025, 07, 17));

        flatBookingRepository.save(flatBooking);
        return flatBooking;
    }

    @Override
    public FlatBooking viewBookingById(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }

        FlatBooking fbooking = optionalBooking.get();
        return fbooking;
    }

    @Override
    public List<FlatBooking> viewAllBookings() {
        List<FlatBooking> fBookingList = flatBookingRepository.findAll();
        return fBookingList;
    }

    @Override
    public void deleteBooking(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }
        FlatBooking flatBooking = optionalBooking.get();
        flatBookingRepository.delete(flatBooking);
    }
    
    @Override
    public void acceptBooking(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }
        FlatBooking flatBooking = optionalBooking.get();
        flatBooking.setBookingStatus("accepted");
        flatBookingRepository.save(flatBooking);
    }

    @Override
    public void denyBooking(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }
        FlatBooking flatBooking = optionalBooking.get();
        flatBooking.setBookingStatus("denied");
        flatBookingRepository.save(flatBooking);
        
    }
    

    @Override
	public String viewBookingStatusbyId(int bookingId) {
		 Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
		    if (optionalBooking.isEmpty()) {
		        throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
		    }
		    FlatBooking flatBooking = optionalBooking.get();
		    return flatBooking.getBookingStatus();
	}

	@Override
    public FlatBooking getBookingDetailsById(int bookingId) {
        Optional<FlatBooking> optionalBooking = flatBookingRepository.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            throw new FlatBookingNotFoundException("Flat booking does not exist with id" + bookingId);
        }

        FlatBooking fbooking = optionalBooking.get();
        return fbooking;
    }
	
	
}