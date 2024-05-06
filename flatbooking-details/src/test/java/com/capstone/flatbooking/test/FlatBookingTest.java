package com.capstone.flatbooking.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.flatbooking.entity.FlatBooking;
import com.capstone.flatbooking.exception.FlatBookingNotFoundException;
import com.capstone.flatbooking.repository.FlatBookingRepository;
import com.capstone.flatbooking.service.FlatBookingServiceImpl;

@SpringBootTest(properties = "eureka.client.enabled=false")

public class FlatBookingTest {
	
		@InjectMocks
		private FlatBookingServiceImpl flatBookingService;
		@Mock
		private FlatBookingRepository flatBookingRepository;
		
		@Test
		public void placeAFlatBooking() {
			
			FlatBooking flatBooking=new FlatBooking();
			
			flatBooking.setBookingId(1);
			flatBooking.setBookingDate(LocalDate.of(2022, 06, 05));
			flatBooking.setBookingFromDate(LocalDate.of(2021, 06, 03));
			flatBooking.setBookingToDate(LocalDate.of(2021, 06, 20));
			
			  when(flatBookingRepository.save(flatBooking)).thenReturn(flatBooking);

			  FlatBooking addedflatBooking = flatBookingService.placeAFlatBooking(flatBooking);

			    verify(flatBookingRepository, times(1)).save(flatBooking);
			    assertEquals(flatBooking, addedflatBooking);
		}
		@Test
		public void viewBookingById() {
	FlatBooking flatBooking=new FlatBooking();
			
	       flatBooking.setBookingId(1);
	       flatBooking.setBookingDate(LocalDate.of(2022, 06, 05));
	       flatBooking.setBookingFromDate(LocalDate.of(2021, 06, 03));
	       flatBooking.setBookingToDate(LocalDate.of(2021, 06, 20));
			
			when(flatBookingRepository.findById(1)).thenReturn(Optional.of(flatBooking));
			FlatBooking actualObj=flatBookingService.getBookingDetailsById(1);
			assertEquals(LocalDate.of(2022, 06, 05),actualObj.getBookingDate());
		}
		@Test
		public void viewBookingByIdException() {
			
			when(flatBookingRepository.findById(200)).thenThrow(new FlatBookingNotFoundException("Landlord is not existing with id:200"));
			assertThrows(FlatBookingNotFoundException.class, () -> flatBookingService.getBookingDetailsById(200));
		}
		@Test
		public void testviewAllBooking() {
			
	        FlatBooking flatBooking=new FlatBooking();
			
	        flatBooking.setBookingId(1);
			flatBooking.setBookingDate(LocalDate.of(2022, 06, 05));
			flatBooking.setBookingFromDate(LocalDate.of(2021, 06, 03));
			flatBooking.setBookingToDate(LocalDate.of(2021, 06, 20));
	        FlatBooking flatBooking1=new FlatBooking();
			
	        flatBooking1.setBookingId(2);
			flatBooking1.setBookingDate(LocalDate.of(2021, 07, 07));
			flatBooking1.setBookingFromDate(LocalDate.of(2022, 06, 07));
			flatBooking1.setBookingToDate(LocalDate.of(2022, 06, 23));
			
			List<FlatBooking> flatBookings=new ArrayList<>();	
			flatBookings.add(flatBooking);
			flatBookings.add(flatBooking);
			when(flatBookingRepository.findAll()).thenReturn(flatBookings);
			List<FlatBooking> flatBookingList=flatBookingService.viewAllBookings();
			assertEquals(2,flatBookingList.size());
			
		}
		@Test
		public void testdeleteBooking() {
			 FlatBooking flatBooking=new FlatBooking();
				
			 flatBooking.setBookingId(1);
				flatBooking.setBookingDate(LocalDate.of(2022, 06, 05));
				flatBooking.setBookingFromDate(LocalDate.of(2021, 06, 03));
				flatBooking.setBookingToDate(LocalDate.of(2021, 06, 20));
			
			when(flatBookingRepository.findById(1)).thenReturn(Optional.of(flatBooking));
			doNothing().when(flatBookingRepository).delete(flatBooking);

			flatBookingService.deleteBooking(1);
			verify(flatBookingRepository, times(1)).findById(1);
		}
		@Test
		public void testdeleteBookingException() {
			FlatBooking flatBooking=new FlatBooking();
			
			flatBooking.setBookingId(1);
			flatBooking.setBookingDate(LocalDate.of(2022, 06, 05));
			flatBooking.setBookingFromDate(LocalDate.of(2021, 06, 03));
			
			when(flatBookingRepository.findById(1)).thenThrow(new FlatBookingNotFoundException("landlord is not existing with id:1"));
			assertThrows(FlatBookingNotFoundException.class, () -> flatBookingService.deleteBooking(1));
			verify(flatBookingRepository, times(0)).delete(flatBooking);
			
		}
	}