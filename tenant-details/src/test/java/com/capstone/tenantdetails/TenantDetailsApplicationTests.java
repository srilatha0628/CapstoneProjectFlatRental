package com.capstone.tenantdetails;

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

import com.capstone.tenantdetails.entity.Tenant;
import com.capstone.tenantdetails.entity.TenantAddress;
import com.capstone.tenantdetails.exception.TenantNotFoundException;
import com.capstone.tenantdetails.repository.TenantRepository;
import com.capstone.tenantdetails.service.TenantServiceImpl;

@SpringBootTest(properties = "eureka.client.enabled=false")
	public class TenantDetailsApplicationTests {
		@InjectMocks
		private TenantServiceImpl tenantService;
		
		@Mock
		private TenantRepository tenantRepository;
		
		@Test
		public void testDeleteTenant(){
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(100);
			tenant.setTenantName("Swathi");
			tenant.setTenantAge(21);
			tenantAddress.setId(1);
			tenantAddress.setHouseNo(101);
			tenantAddress.setCity("Kakinada");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(533434);
			tenantAddress.setState("AP");
			tenantAddress.setStreet("Gn");
			
	        when(tenantRepository.findById(100)).thenReturn(Optional.of(tenant));
			
			doNothing().when(tenantRepository).delete(tenant);
			
			tenantService.removeTenant(100);
			
			verify(tenantRepository,times(1)).findById(100);
			verify(tenantRepository,times(1)).delete(tenant);
			
	  	}
		@Test
		public void testViewTenant(){
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(200);
			tenant.setTenantName("Siri");
			tenant.setTenantAge(24);
			tenantAddress.setId(2);
			tenantAddress.setHouseNo(201);
			tenantAddress.setCity("Hyderabad");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(533444);
			tenantAddress.setState("ts");
			tenantAddress.setStreet("kphb");
			
			when(tenantRepository.findById(200)).thenReturn(Optional.of(tenant));
			Tenant actualObj=tenantService.viewTenantById(200);
			assertEquals("Siri",actualObj.getTenantName());
			
	  	}
		@Test
		 public void testViewAllTenants(){
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(300);
			tenant.setTenantName("Aswini");
			tenant.setTenantAge(23);
			tenantAddress.setId(3);
			tenantAddress.setHouseNo(301);
			tenantAddress.setCity("Sml");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(534534);
			tenantAddress.setState("ap");
			tenantAddress.setStreet("Kothapeta");
			
			Tenant tenant1= new Tenant();
			TenantAddress tenantAddress1= new TenantAddress();
			tenant1.setTenantId(400);
			tenant1.setTenantName("Jyothi");
			tenant1.setTenantAge(26);
			tenantAddress1.setId(4);
			tenantAddress1.setHouseNo(401);
			tenantAddress1.setCity("Hyd");
			tenantAddress1.setCountry("India");
			tenantAddress1.setPin(500341);
			tenantAddress1.setState("Ap");
			tenantAddress1.setStreet("miyapur");
			
			Tenant tenant2= new Tenant();
			TenantAddress tenantAddress2= new TenantAddress();
			tenant2.setTenantId(500);
			tenant2.setTenantName("Reshma");
			tenant2.setTenantAge(26);
			tenantAddress2.setId(5);
			tenantAddress2.setHouseNo(501);
			tenantAddress2.setCity("Rajamundry");
			tenantAddress2.setCountry("India");
			tenantAddress2.setPin(533331);
			tenantAddress2.setState("Ap");
			tenantAddress2.setStreet("Davao");
			
			List<Tenant>tenants= new ArrayList<>();
			tenants.add(tenant);
			tenants.add(tenant1);
			tenants.add(tenant2);
			
	        when(tenantRepository.findAll()).thenReturn(tenants);
			
			List<Tenant>tenantList=tenantService.viewAllTenants();
			assertEquals(3,tenantList.size());
			
			

	}
		@Test
		 public void testDeleteTenantWihException() {
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(300);
			tenant.setTenantName("Madhu");
			tenant.setTenantAge(22);
			tenantAddress.setId(3);
			tenantAddress.setHouseNo(105);
			tenantAddress.setCity("vijayawada");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(500543);
			tenantAddress.setState("ap");
			tenantAddress.setStreet("Gandhi");
			
	        when(tenantRepository.findById(300)).thenThrow(new TenantNotFoundException("Tenant is not existing with id:300"));
			
			assertThrows(TenantNotFoundException.class,()->tenantService.removeTenant(300));
			
			verify(tenantRepository,times(0)).delete(tenant);
		}
		@Test
		  public void testUpdateTenantWihException() {
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(100);
			tenant.setTenantName("Swathi");
			tenant.setTenantAge(21);
			tenantAddress.setId(1);
			tenantAddress.setHouseNo(101);
			tenantAddress.setCity("Kakinada");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(533433);
			tenantAddress.setState("AP");
			tenantAddress.setStreet("Gandhinagar");
			
			when(tenantRepository.findById(100)).thenReturn(Optional.empty());

		    assertThrows(TenantNotFoundException.class, () -> tenantService.updateTenantdetails(tenant));
		    verify(tenantRepository, times(1)).findById(100);
		    verify(tenantRepository, times(0)).save(tenant);
		}
		@Test
		public void testViewTenantWithException() {
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(200);
			tenant.setTenantName("Navya");
			tenant.setTenantAge(21);
			tenantAddress.setId(2);
			tenantAddress.setHouseNo(27);
			tenantAddress.setCity("vizag");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(533444);
			tenantAddress.setState("Ap");
			tenantAddress.setStreet("Rk");
			
			when(tenantRepository.findById(200)).thenThrow(new TenantNotFoundException("Tenant is not existing with id:200"));
			assertThrows(TenantNotFoundException.class, () -> tenantService.viewTenantById(200));
		}
		@Test
		public void testsaveTenant() {
			Tenant tenant= new Tenant();
			TenantAddress tenantAddress= new TenantAddress();
			tenant.setTenantId(200);
			tenant.setTenantName("Navya");
			tenant.setTenantAge(27);
			tenantAddress.setId(2);
			tenantAddress.setHouseNo(20);
			tenantAddress.setCity("vizag");
			tenantAddress.setCountry("India");
			tenantAddress.setPin(533444);
			tenantAddress.setState("Ap");
			tenantAddress.setStreet("Rk");
			tenant.setTenantAddress(tenantAddress);
			
			 when(tenantRepository.findById(tenant.getTenantId())).thenReturn(null);

		        Tenant savedTenant = tenantService.addTenantdetails(tenant);

		        verify(tenantRepository, times(1)).save(tenant);

		        
		        assertNotNull(savedTenant);

		        assertEquals(tenant.getTenantName(), savedTenant.getTenantName());
		        assertEquals(tenant.getTenantAge(), savedTenant.getTenantAge());
		        assertEquals(tenant.getTenantAddress().getHouseNo(), savedTenant.getTenantAddress().getHouseNo());
		        assertEquals(tenant.getTenantAddress().getCity(), savedTenant.getTenantAddress().getCity());
		        assertEquals(tenant.getTenantAddress().getCountry(), savedTenant.getTenantAddress().getCountry());
		    }
		
		@Test
		public void testUpdateTenant() {
			 Tenant tenant = new Tenant();
		        tenant.setTenantId(1);
		        tenant.setTenantName("Swathi");
		        tenant.setTenantAge(21);
		        TenantAddress tenantAddress = new TenantAddress();
		        tenantAddress.setHouseNo(152);
		        tenantAddress.setCity("New York");
		        tenantAddress.setCountry("USA");
		        tenant.setTenantAddress(tenantAddress);

		        when(tenantRepository.findById(1)).thenReturn(Optional.of(tenant));
			    when(tenantRepository.save(tenant)).thenReturn(tenant);

			    tenant.setTenantName("Swathi");
		        tenant.setTenantAge(22);
		        TenantAddress updatedTenantAddress = new TenantAddress();
		        updatedTenantAddress.setHouseNo(405);
		        updatedTenantAddress.setCity("Guntur");
		        updatedTenantAddress.setCountry("Ap");
		        tenant.setTenantAddress(updatedTenantAddress);
		        
			    Tenant updatedTenant = tenantService.updateTenantdetails(tenant);

			    verify(tenantRepository, times(1)).findById(1);
			    verify(tenantRepository, times(1)).save(tenant);
			    assertEquals(tenant, updatedTenant);
			    assertEquals("Swathi", updatedTenant.getTenantName());
			    assertEquals(22, updatedTenant.getTenantAge());
		        assertEquals(tenant.getTenantAddress().getHouseNo(), updatedTenant.getTenantAddress().getHouseNo());
		        assertEquals(tenant.getTenantAddress().getCity(), updatedTenant.getTenantAddress().getCity());
		        assertEquals(tenant.getTenantAddress().getCountry(), updatedTenant.getTenantAddress().getCountry());
		    }

	}

