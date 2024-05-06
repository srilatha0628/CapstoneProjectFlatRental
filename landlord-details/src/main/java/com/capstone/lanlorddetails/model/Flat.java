package com.capstone.lanlorddetails.model;

public class Flat {

    private int flatId;
    private float price;
    private String availability;
    private FlatAddress flatAddress;
    private  int lanlordId;

    public int getLanlordId() {
		return lanlordId;
	}

	public void setLanlordId(int lanlordId) {
		this.lanlordId = lanlordId;
	}

	public int getFlatId() {
        return flatId;
    }

    public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public float getprice() {
        return price;
    }

    public void setprice(float price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public FlatAddress getFlatAddress() {
        return flatAddress;
    }

    public void setFlatAddress(FlatAddress flatAddress) {
        this.flatAddress = flatAddress;
    }
}
