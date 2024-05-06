package com.capstone.lanlorddetails.controller;

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

import com.capstone.lanlorddetails.entity.Landlord;
import com.capstone.lanlorddetails.model.Flat;
import com.capstone.lanlorddetails.model.FlatBooking;
import com.capstone.lanlorddetails.model.Tenant;
import com.capstone.lanlorddetails.service.FlatBookingConsumer;
import com.capstone.lanlorddetails.service.FlatServiceConsumer;
import com.capstone.lanlorddetails.service.LandlordService;
import com.capstone.lanlorddetails.service.TenantServiceConsumer;

@RestController
@RequestMapping("/landlorddetails")
public class LandlordController {
	@Autowired
	private LandlordService landlordService;
	@Autowired
	private FlatServiceConsumer flatconsumer;

	@Autowired
	private TenantServiceConsumer tenantConsumer;

	@Autowired
	private FlatBookingConsumer flatBookingConsumer;

	@GetMapping("/all")
	public List<Landlord> fetchAllLandlords() {
		List<Landlord> landlords = landlordService.viewAllLandlords();
		return landlords;
	}

	public LandlordService getLandlordService() {
		return landlordService;
	}

	public void setLandlordService(LandlordService landlordService) {
		this.landlordService = landlordService;
	}

	public FlatServiceConsumer getFlatconsumer() {
		return flatconsumer;
	}

	public void setFlatconsumer(FlatServiceConsumer flatconsumer) {
		this.flatconsumer = flatconsumer;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Landlord> fetchLandlordDetails(@PathVariable("id") int landlordId) {
		Landlord landlord = landlordService.viewLandlord(landlordId);
		return new ResponseEntity<>(landlord, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Landlord> addLanlord(@RequestBody Landlord landlord) {
		landlordService.addLandlorddetails(landlord);
		ResponseEntity<Landlord> responseEntity = new ResponseEntity<>(landlord, HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping("/update")
	public ResponseEntity<Landlord> editLanlord(@RequestBody Landlord landlord) {
		landlordService.updateLandlorddetails(landlord);
		ResponseEntity<Landlord> responseEntity = new ResponseEntity<>(landlord, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLanlord(@PathVariable("id") int landlordId) {
		landlordService.removeLandlord(landlordId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;

	}
	@PostMapping("/flatdetails/adddetails")
	public ResponseEntity<Flat> addFlat(@RequestBody Flat flat) {
		flatconsumer.addFlatdetails(flat);
		ResponseEntity<Flat> responseEntity = new ResponseEntity<>(flat, HttpStatus.CREATED);
		return responseEntity;
	}
	


	@GetMapping("/tenantdetails/viewtenantid/{tenantId}")
	public ResponseEntity<Tenant> fetchTenantDetails(@PathVariable("tenantId") int tenantId) {
		Tenant tenants = tenantConsumer.getTenantDetails(tenantId);
		return new ResponseEntity<>(tenants, HttpStatus.OK);

	}

	@PostMapping("/flatbooking/acceptbooking/{id}")
	public ResponseEntity<FlatBooking> acceptFlatBooking(@PathVariable("id") int bookingId) {
		flatBookingConsumer.acceptBooking(bookingId);
		ResponseEntity<FlatBooking> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;

	}

	@PostMapping("/flatbooking/denybooking/{id}")
	public ResponseEntity<FlatBooking> denytFlatBooking(@PathVariable("id") int bookingId) {
		flatBookingConsumer.denyBooking(bookingId);
		ResponseEntity<FlatBooking> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;

	}
}