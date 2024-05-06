package com.capstone.tenantdetails.service;
import java.util.List;

import com.capstone.tenantdetails.entity.Tenant;
import com.capstone.tenantdetails.model.FlatBooking;

public interface TenantService {
	
	Tenant addTenantdetails(Tenant tenant);

	Tenant updateTenantdetails(Tenant tenant);

	void removeTenant(int tenantId);

	Tenant viewTenantById(int tenantId);
	
	List<Tenant> viewAllTenants();
	
	FlatBooking addBooking(FlatBooking flatBooking);
	
	String viewBookingStatus(int bookingId);
}

