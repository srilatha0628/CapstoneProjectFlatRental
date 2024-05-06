package com.capstone.flatdetails.service;

import java.util.List;

import com.capstone.flatdetails.entity.Flat;

public interface FlatService {

	Flat addFlatdetails(Flat flat);

	Flat updateFlatdetails(Flat flat);

	void removeFlatById(int flatId);

	Flat viewFlatById(int flatId);

	List<Flat> viewAllFlats();

	List<Flat> viewAllFlatsByCost(float cost);
}

