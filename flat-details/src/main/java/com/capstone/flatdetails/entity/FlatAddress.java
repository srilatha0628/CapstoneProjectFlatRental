package com.capstone.flatdetails.entity;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	@Entity
	public class FlatAddress {
	    
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int AddressId;
	    private String houseNo;
	    private String street;
	    private String city;
	    private String state;
	    private int pin;
	    private String country;	
	    
	   
		
		public int getAddressId() {
			return AddressId;
		}
		public void setAddressId(int addressId) {
			AddressId = addressId;
		}
		public String getHouseNo() {
			return houseNo;
		
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public int getPin() {
			return pin;
		}
		public void setPin(int pin) {
			this.pin = pin;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		
		}
		public void setHouseNo(String houseNo) {
			this.houseNo = houseNo;
		}
	}

