package com.capstone.tenantdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.tenantdetails.entity.Tenant;
import com.capstone.tenantdetails.model.Flat;
import com.capstone.tenantdetails.model.FlatBooking;
import com.capstone.tenantdetails.service.FlatBookingConsumer;
import com.capstone.tenantdetails.service.FlatServiceConsumer;
import com.capstone.tenantdetails.service.TenantService;

@RestController
@RequestMapping("/tenantdetails")
public class TenantController {
	@Autowired
    private TenantService tenantService;
	
	@Autowired
	private FlatServiceConsumer FLatService;
	
	@Autowired
	private FlatBookingConsumer FlatBooking;

    @PostMapping("/add")
    public ResponseEntity<Tenant> addTenant(@RequestBody Tenant tenant) {
    	tenantService.addTenantdetails(tenant);
		ResponseEntity<Tenant> responseEntity = new ResponseEntity<>(tenant,HttpStatus.CREATED);
		return responseEntity;
	}
    @PutMapping("/update")
    public ResponseEntity<Tenant> updateTenant(@RequestBody Tenant tenant) {
    	tenantService.updateTenantdetails(tenant);
		ResponseEntity<Tenant> responseEntity= new ResponseEntity<>(tenant, HttpStatus.OK);
		return responseEntity;
	}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable("id") int tenantId) {
    	tenantService.removeTenant(tenantId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

    @GetMapping("/viewtenantid/{tenantId}")
    public ResponseEntity<Tenant> viewTenant(@PathVariable("tenantId") int tenantId) {
        Tenant tenant= tenantService.viewTenantById(tenantId);
        return new ResponseEntity<>(tenant,HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Tenant> viewAllTenants() {
        List<Tenant> tenants=tenantService.viewAllTenants();
        return tenants;
    
    }
    
    @PostMapping("/flatbooking/requestbooking")
    public ResponseEntity<FlatBooking> addBooking(@RequestBody FlatBooking flatBooking) {
    	FlatBooking.addBooking(flatBooking);
		ResponseEntity<FlatBooking> responseEntity = new ResponseEntity<>(flatBooking,HttpStatus.CREATED);
		return responseEntity;
	}
    
    @GetMapping("/flatbooking/status/{id}")
    public String viewBookingStatus(@PathVariable("id") int bookingId) {    	
    	String bookingStatus = FlatBooking.viewBookingStatus(bookingId);
		return bookingStatus;
    	
    }
    
    @GetMapping("/flatdetails/all")
    public List<Flat> viewAllFlats() {    	
    	List<Flat> FlatList = FLatService.viewAllAvailableFlats();
		return  FlatList;
		
	
    }
}