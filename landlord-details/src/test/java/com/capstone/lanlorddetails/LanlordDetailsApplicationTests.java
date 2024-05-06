package com.capstone.lanlorddetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.capstone.lanlorddetails.entity.Landlord;
import com.capstone.lanlorddetails.exception.LandlordNotFoundException;
import com.capstone.lanlorddetails.repository.LandlordRepository;
import com.capstone.lanlorddetails.service.LandlordServiceImpl;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class LanlordDetailsApplicationTests {
	@InjectMocks
	private LandlordServiceImpl landlordService;
	@Mock
	private LandlordRepository landlordRepository;
	@Test
	public void testviewLandlord() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("srilatha");
		landlord.setLandlordAge(22);
		landlord.setMobileNum("6789066712");
		
		when(landlordRepository.findById(111)).thenReturn(Optional.of(landlord));
		Landlord actualObj=landlordService.viewLandlord(111);
		assertEquals("srilatha",actualObj.getLandlordName());
	}
	@Test
	public void testViewLandlordException() {
		
		when(landlordRepository.findById(200)).thenThrow(new LandlordNotFoundException("Landlord is not existing with id:200"));
		assertThrows(LandlordNotFoundException.class, () -> landlordService.viewLandlord(200));
	}
	@Test
	public void testViewAllLandlords() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("srilatha");
		landlord.setLandlordAge(21);
		landlord.setMobileNum("9878787431");
		
		Landlord landlord1=new Landlord();
		landlord1.setLandlordId(222);
		landlord1.setLandlordName("hani");
		landlord1.setLandlordAge(20);
		landlord1.setMobileNum("9708789881");
		
		List<Landlord> landlords=new ArrayList<>();	
		landlords.add(landlord);
		landlords.add(landlord1);
		when(landlordRepository.findAll()).thenReturn(landlords);
		List<Landlord> landlordList=landlordService.viewAllLandlords();
		assertEquals(2,landlordList.size());
		
	}
	@Test
	void testDeleteLandlord() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("srilatha");
		landlord.setLandlordAge(22);
		landlord.setMobileNum("6789066712");

		when(landlordRepository.findById(111)).thenReturn(Optional.of(landlord));
		doNothing().when(landlordRepository).delete(landlord);

		landlordService.removeLandlord(111);;
		verify(landlordRepository, times(1)).findById(111);
		verify(landlordRepository, times(1)).delete(landlord);
		
	}
	@Test
	void testDeleteLandlordException() {
		Landlord landlord=new Landlord();
		landlord.setLandlordId(111);
		landlord.setLandlordName("srilatha");
		landlord.setLandlordAge(22);
		landlord.setMobileNum("6789066712");

		when(landlordRepository.findById(111)).thenThrow(new LandlordNotFoundException("landlord is not existing with id:111"));
		assertThrows(LandlordNotFoundException.class, () -> landlordService.removeLandlord(111));
		verify(landlordRepository, times(0)).delete(landlord);
		
	}
	@Test
	public void testAddLandlord() {
	    Landlord landlord = new Landlord();
	    landlord.setLandlordName("srilatha");
	    landlord.setLandlordAge(22);
	    landlord.setMobileNum("6789066712");

	    when(landlordRepository.save(landlord)).thenReturn(landlord);

	    Landlord addedLandlord = landlordService.addLandlorddetails(landlord);

	    verify(landlordRepository, times(1)).save(landlord);
	    assertEquals(landlord, addedLandlord);
	    assertEquals("srilatha", addedLandlord.getLandlordName());
	    assertEquals(22, addedLandlord.getLandlordAge());
	    assertEquals("6789066712", addedLandlord.getMobileNum());
	}
	@Test
	public void testUpdateLandlord() {
	    Landlord landlord = new Landlord();
	    landlord.setLandlordId(1);
	    landlord.setLandlordName("srilatha");
	    landlord.setLandlordAge(22);
	    landlord.setMobileNum("9122456711");

	    when(landlordRepository.findById(1)).thenReturn(Optional.of(landlord));
	    when(landlordRepository.save(landlord)).thenReturn(landlord);

	    landlord.setLandlordName("srilatha");
	    landlord.setLandlordAge(23);
	    landlord.setMobileNum("6890786121");

	    Landlord updatedLandlord = landlordService.updateLandlorddetails(landlord);

	    verify(landlordRepository, times(1)).findById(1);
	    verify(landlordRepository, times(1)).save(landlord);
	    assertEquals(landlord, updatedLandlord);
	    assertEquals("srilatha", updatedLandlord.getLandlordName());
	    assertEquals(23, updatedLandlord.getLandlordAge());
	    assertEquals("6890786121", updatedLandlord.getMobileNum());
	}
	@Test
	public void testUpdateLandlordWithException() {
	    Landlord landlord = new Landlord();
	    landlord.setLandlordId(1);
	    landlord.setLandlordName("suppi");
	    landlord.setLandlordAge(21);
	    landlord.setMobileNum("6309087621");

	    when(landlordRepository.findById(1)).thenReturn(Optional.empty());

	    assertThrows(LandlordNotFoundException.class, () -> landlordService.updateLandlorddetails(landlord));
	    verify(landlordRepository, times(1)).findById(1);
	    verify(landlordRepository, times(0)).save(landlord);
	}
}
