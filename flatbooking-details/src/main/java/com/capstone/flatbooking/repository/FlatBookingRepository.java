package com.capstone.flatbooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.flatbooking.entity.FlatBooking;

public interface FlatBookingRepository extends JpaRepository<FlatBooking,Integer>{

}