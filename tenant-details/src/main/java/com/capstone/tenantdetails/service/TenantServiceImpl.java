package com.capstone.tenantdetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.tenantdetails.entity.Tenant;
import com.capstone.tenantdetails.exception.TenantNotFoundException;
import com.capstone.tenantdetails.model.FlatBooking;
import com.capstone.tenantdetails.repository.TenantRepository;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    
    @Autowired
    private FlatBookingConsumer bookingService;

    @Override
    public Tenant addTenantdetails(Tenant tenant) {
        tenantRepository.save(tenant);
        return tenant;
    }

    @Override
    public Tenant updateTenantdetails(Tenant tenant) {
    	Optional<Tenant> optionalTenant = tenantRepository.findById(tenant.getTenantId());
		if(optionalTenant.isEmpty()) {
			throw new TenantNotFoundException("Tenant not existing with id: "+ tenant.getTenantId());
		}
		
		tenantRepository.save(tenant);		
		return tenant;
	}


    @Override
    public void removeTenant(int tenantId) {
    	Optional<Tenant> optionalTenant = tenantRepository.findById(tenantId);
		if(optionalTenant.isEmpty()) {			
			
			throw new TenantNotFoundException("Tenant not existing with id: "+tenantId);
			
		}
		Tenant tenant = optionalTenant.get();
		tenantRepository.delete(tenant);		
		
	}
    @Override
    public Tenant viewTenantById(int tenantId) {
    	Optional<Tenant> optionalTenant = tenantRepository.findById(tenantId);
		if(optionalTenant.isEmpty()) {
			throw new TenantNotFoundException("Tenant not existing with id: "+tenantId);
			
		}
		Tenant tenant = optionalTenant.get();
		return tenant;

			}
    @Override
    public List<Tenant> viewAllTenants() {
    	List<Tenant> tenants= tenantRepository.findAll();
		return tenants;
	}

	@Override
	public FlatBooking addBooking(FlatBooking flatBooking) {
		FlatBooking flat = bookingService.addBooking(flatBooking);
		return flat;
	}

	@Override
	public String viewBookingStatus(int bookingId) {
		String bookingStatus= bookingService.viewBookingStatus(bookingId);
		return bookingStatus;
	}
}