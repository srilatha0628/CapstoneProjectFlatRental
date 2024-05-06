package com.capstone.lanlorddetails.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.lanlorddetails.entity.Landlord;

public interface LandlordRepository extends JpaRepository<Landlord, Integer> {
  
}
