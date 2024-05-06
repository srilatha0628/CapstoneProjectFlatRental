package com.capstone.lanlorddetails.service;
import java.util.List;
import com.capstone.lanlorddetails.entity.Landlord;
import com.capstone.lanlorddetails.model.Flat;
public interface LandlordService {
	
	Landlord addLandlorddetails(Landlord landlord);

	Landlord updateLandlorddetails(Landlord landlord);

	void removeLandlord(int landlordId);

	Landlord viewLandlord(int landlordId);
	
	Flat saveFlat(Flat flat);
	
	List<Landlord> viewAllLandlords();
	
}
