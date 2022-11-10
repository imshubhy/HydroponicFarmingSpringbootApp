package com.masai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.masai.model.User;
import com.masai.model.Crop;
import com.masai.model.LogDetails;
import com.masai.services.CropServiceIntr;
import com.masai.services.UserServiceIntr;



@RestController
public class GetDetailsController {
	
	@Autowired
	private UserServiceIntr userServiceImpl;
	
	@Autowired
	private CropServiceIntr cropService;

	
	@Autowired
	private com.masai.services.LogDetails logDetails;
	
	// to get all log details
	@GetMapping("/logdetails")
	public ResponseEntity<List<LogDetails> > getListOfLogDetailsHandler(){
	  List<LogDetails>	list = logDetails.logDetailsList();
	  
	  return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	
	// To get details of current user by passing its login key
	@GetMapping(value = "/user")
	public ResponseEntity<User> getUserDetailsHandler(@RequestParam(required = false) String key) {
		User fetched_customer = userServiceImpl.getUserDetails(key);

		return new ResponseEntity<User>(fetched_customer, HttpStatus.ACCEPTED);
	}
	
	
	
	
	// customer list
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getAllUserDetailsHandler() {
		List<User> list = userServiceImpl.getUserList();
		
		return new ResponseEntity<List<User>>(list, HttpStatus.ACCEPTED);
	}
	
	
	
	@GetMapping(value = "/user/crops")
	public ResponseEntity<List<Crop>> getListOfCropsHandler(@RequestParam String key){
		
	  List<Crop> list =	cropService.showListOfCrops(key);
	  
	  return new ResponseEntity<List<Crop>>(list,HttpStatus.ACCEPTED);
	}
	

	
	
}
