package com.capstone.flatdetails;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.flatdetails.entity.Flat;
import com.capstone.flatdetails.entity.FlatAddress;
import com.capstone.flatdetails.exception.FlatNotFoundException;
import com.capstone.flatdetails.repository.FlatRepository;
import com.capstone.flatdetails.service.FlatServiceImpl;


@SpringBootTest(properties = "eureka.client.enabled=false")
public class FlatServiceTest {
	@InjectMocks
	private FlatServiceImpl flatService;

	@Mock
	private FlatRepository flatRepository;

	@Test
	public void testDeleteFlat() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("Kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("Gandhinagar");

		when(flatRepository.findById(100)).thenReturn(Optional.of(flat));

		doNothing().when(flatRepository).delete(flat);

		flatService.removeFlatById(100);

		verify(flatRepository, times(1)).findById(100);
		verify(flatRepository, times(1)).delete(flat);
	}

	@Test
	public void testViewFlatById() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(200);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("Kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("Gandhinagar");

		when(flatRepository.findById(200)).thenReturn(Optional.of(flat));
		Flat actualObj = flatService.viewFlatById(200);
		assertEquals("yes", actualObj.getAvailability());
	}

	@Test
	public void testViewAllFlats() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("Kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("gandhinagar");

		Flat flat1 = new Flat();
		FlatAddress flatAddress1 = new FlatAddress();
		flat1.setFlatId(200);
		flat1.setCost(60000);
		flat1.setAvailability("yes");
		flatAddress1.setCity("Hyd");
		flatAddress1.setCountry("India");
		flatAddress1.setPin(544334);
		flatAddress1.setState("Telangana");
		flatAddress1.setStreet("Kphb");

		Flat flat2 = new Flat();
		FlatAddress flatAddress2 = new FlatAddress();
		flat2.setFlatId(300);
		flat2.setCost(20000);
		flat2.setAvailability("no");
		flatAddress2.setCity("Hyd");
		flatAddress2.setCountry("India");
		flatAddress2.setPin(533444);
		flatAddress2.setState("Telangana");
		flatAddress2.setStreet("Miyapur");

		List<Flat> flats = new ArrayList<>();
		flats.add(flat);
		flats.add(flat1);
		flats.add(flat2);

		when(flatRepository.findAll()).thenReturn(flats);

		List<Flat> flatList = flatService.viewAllFlats();
		assertEquals(3, flatList.size());
	}

	@Test
	public void testAddFlat() {

		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("Kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("GandhiNagar");
		flat.setFlatAddress(flatAddress);

		when(flatRepository.save(flat)).thenReturn(flat);

		Flat savedFlat = flatService.addFlatdetails(flat);

		verify(flatRepository, times(1)).save(flat);

		assertNotNull(savedFlat);

		assertEquals(flat.getAvailability(), savedFlat.getAvailability());
		assertEquals(flat.getCost(), savedFlat.getCost());
		assertEquals(flat.getFlatId(), savedFlat.getFlatId());
		assertEquals(flat.getFlatAddress().getHouseNo(), savedFlat.getFlatAddress().getHouseNo());
		assertEquals(flat.getFlatAddress().getCity(), savedFlat.getFlatAddress().getCity());
		assertEquals(flat.getFlatAddress().getCountry(), savedFlat.getFlatAddress().getCountry());
		assertEquals(flat.getFlatAddress().getState(), savedFlat.getFlatAddress().getState());
		assertEquals(flat.getFlatAddress().getStreet(), savedFlat.getFlatAddress().getStreet());
		assertEquals(flat.getFlatAddress().getPin(), savedFlat.getFlatAddress().getPin());

	}

	@Test
	public void testUpdateFlat() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("gandhinagar");
		flat.setFlatAddress(flatAddress);

		when(flatRepository.findById(100)).thenReturn(Optional.of(flat));
		when(flatRepository.save(flat)).thenReturn(flat);

		flat.setCost(70000);
		flat.setAvailability("yes");
		flatAddress.setCity("Sml");
		flatAddress.setCountry("India");
		flatAddress.setPin(533212);
		flatAddress.setState("AP");
		flatAddress.setStreet("Xyz");
		flat.setFlatAddress(flatAddress);

		Flat updatedFlat = flatService.updateFlatdetails(flat);

		verify(flatRepository, times(1)).findById(100);
		verify(flatRepository, times(1)).save(flat);

		assertEquals(flat, updatedFlat);
		assertEquals("yes", updatedFlat.getAvailability());
		assertEquals(70000, updatedFlat.getCost());
		assertEquals(flat.getFlatAddress().getHouseNo(), updatedFlat.getFlatAddress().getHouseNo());
		assertEquals(flat.getFlatAddress().getCity(), updatedFlat.getFlatAddress().getCity());
		assertEquals(flat.getFlatAddress().getCountry(), updatedFlat.getFlatAddress().getCountry());

	}

	@Test
	public void testUpdateFlatWihException() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("Kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("Gandhinagar");

		when(flatRepository.findById(100)).thenReturn(Optional.empty());

		assertThrows(FlatNotFoundException.class, () -> flatService.updateFlatdetails(flat));
		verify(flatRepository, times(1)).findById(100);
		verify(flatRepository, times(0)).save(flat);
	}

	@Test
	public void testDeleteTenantWihException() {
		Flat flat = new Flat();
		FlatAddress flatAddress = new FlatAddress();
		flat.setFlatId(100);
		flat.setCost(50000);
		flat.setAvailability("yes");
		flatAddress.setCity("Kkd");
		flatAddress.setCountry("India");
		flatAddress.setPin(533434);
		flatAddress.setState("AP");
		flatAddress.setStreet("Gandhinagar");

		when(flatRepository.findById(100)).thenThrow(new FlatNotFoundException("Tenant is not existing with id:100"));

		assertThrows(FlatNotFoundException.class, () -> flatService.removeFlatById(100));

		verify(flatRepository, times(0)).delete(flat);
	}

}