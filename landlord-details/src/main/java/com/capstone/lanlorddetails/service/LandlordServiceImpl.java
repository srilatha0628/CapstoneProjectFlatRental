package com.capstone.lanlorddetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.lanlorddetails.entity.Landlord;
import com.capstone.lanlorddetails.exception.LandlordNotFoundException;
import com.capstone.lanlorddetails.model.Flat;
import com.capstone.lanlorddetails.repository.LandlordRepository;

@Service
public class LandlordServiceImpl implements LandlordService {
	
	@Autowired
	private LandlordRepository landlordRepository;
	
	@Autowired
	private FlatServiceConsumer flatService;
	

	@Override
	public Landlord addLandlorddetails(Landlord landlord) {
		landlordRepository.save(landlord);
		return landlord;
	}

	@Override
	public Landlord updateLandlorddetails(Landlord landlord) {
		Optional<Landlord> optionalLandlord = landlordRepository.findById(landlord.getLandlordId());
		if (optionalLandlord.isEmpty()) {
			throw new LandlordNotFoundException("Landlord with id "+landlord.getLandlordId()+"does not exist");
		}
		landlordRepository.save(landlord);
		return landlord;
	}

	@Override
	public Landlord viewLandlord(int landlordId) {
		Optional<Landlord> optionalLandlord = landlordRepository.findById(landlordId);
		if (optionalLandlord.isEmpty()) {
			throw new LandlordNotFoundException("Landlord with id "+landlordId+"does not exist");
		}
		Landlord landlord = optionalLandlord.get();
		return landlord;
	}

	@Override
	public List<Landlord> viewAllLandlords() {
		List<Landlord> landlords = landlordRepository.findAll();
		return landlords;
	}

	@Override
	public void removeLandlord(int landlordId) {
		Optional<Landlord> optionalLandlord = landlordRepository.findById(landlordId);
		if (optionalLandlord.isEmpty()) {
			throw new LandlordNotFoundException("Landlord with id "+landlordId+"does not exist");
		}
		Landlord landlord = optionalLandlord.get();
		landlordRepository.delete(landlord);

		
	}
	
	public Flat saveFlat(Flat flat) {
		flatService.addFlatdetails(flat);
		return flat;
	
	}

}