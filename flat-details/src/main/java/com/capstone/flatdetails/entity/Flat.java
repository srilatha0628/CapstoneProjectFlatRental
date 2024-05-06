package com.capstone.flatdetails.entity;
	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.OneToOne;
	import jakarta.persistence.Table;

	@Entity
	@Table(name="flat_tbl")
	public class Flat {
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private int flatId;
	    
	    private float cost;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "flatAddress_id")
	    private FlatAddress flatAddress;
	   
	    private String availability;
		
	    public int getFlatId() {
			return flatId;
		}
		public void setFlatId(int flatId) {
			this.flatId = flatId;
		}
		public float getCost() {
			return cost;
		}
		public void setCost(float cost) {
			this.cost = cost;
		}
		public FlatAddress getFlatAddress() {
			return flatAddress;
		}
		public void setFlatAddress(FlatAddress flatAddress) {
			this.flatAddress = flatAddress;
		}
		public String getAvailability() {
			return availability;
		}
		public void setAvailability(String availability) {
			this.availability = availability;
		}

	   
	}

