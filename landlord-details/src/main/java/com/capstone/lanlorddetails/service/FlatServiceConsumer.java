package com.capstone.lanlorddetails.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capstone.lanlorddetails.model.Flat;

@FeignClient(name = "FLAT-DETAILS")
public interface FlatServiceConsumer {

    @PostMapping("/flatdetails/adddetails")
    Flat addFlatdetails(@RequestBody Flat flat);
    
    @DeleteMapping("/flatdetails/remove/{id}")
    void deleteFlat(@PathVariable("id") int flatId);
  
}
