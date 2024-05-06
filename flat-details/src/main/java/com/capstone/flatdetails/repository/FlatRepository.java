package com.capstone.flatdetails.repository;


	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.capstone.flatdetails.entity.Flat;

	public interface FlatRepository extends JpaRepository<Flat,Integer>{


		List<Flat> findByCost(float flatCost);


	}
