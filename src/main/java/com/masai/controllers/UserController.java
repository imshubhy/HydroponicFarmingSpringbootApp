package com.masai.controllers;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.masai.model.User;
import com.masai.model.Crop;
import com.masai.model.LoginDTO;
import com.masai.services.CropServiceIntr;
import com.masai.services.UserLogImpl;
import com.masai.services.UserServiceIntr;


@RestController
public class UserController {
	
	@Autowired
	private UserServiceIntr userServiceImpl;

	@Autowired
    private CropServiceIntr cropService;
	
	@Autowired
	private UserLogImpl userLoging;
	
	
	  // for user Login
		@PostMapping(value = "user/login")
		public ResponseEntity<String> loginUserHandler(@Valid @RequestBody LoginDTO userDto) {
			String s = userLoging.logIntoAccount(userDto);
			return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
		}
		
		// for user Logout
		@PostMapping(value = "user/logout")
		public ResponseEntity<String> logOutUserHandler(@RequestParam(required = false) String key) {
			String s = userLoging.logOutFromAccount(key);
			return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
		}
		
		

		// to register user
		@PostMapping(value = "/user")
		public ResponseEntity<User> registerUserHandler(@Valid @RequestBody User user) {

			User usr = userServiceImpl.createUser(user);

			return new ResponseEntity<User>(usr, HttpStatus.CREATED);
		}
		
	

		
		// To delete existing user details by passing its login key
		@DeleteMapping(value = "/user")
		public ResponseEntity<User> removeUserHandler(@RequestParam(required = false) String key) {
			User deletedUser = userServiceImpl.removeUser(key);

			return new ResponseEntity<User>(deletedUser, HttpStatus.ACCEPTED);
		}
		
		
		
		 @PostMapping(value = "/crops")
		 public ResponseEntity<Crop> addCrophandler(@RequestBody Crop crop,@RequestParam String key){
			
			Crop addedCrop = cropService.addCrop(crop,key);
			return new ResponseEntity<Crop>(addedCrop,HttpStatus.CREATED);
				 
		 }
			
		 
		 @PutMapping(value = "/crops/{id}")
		 public ResponseEntity<Crop> udpateCropHandler(@RequestBody Crop crop , @PathVariable Integer id, @RequestParam String key){
		   Crop updatedCrop = cropService.updateCropDetails(crop, id, key);
			 return new ResponseEntity<Crop>(updatedCrop,HttpStatus.ACCEPTED);
			 
		 }
		
	
		
	

}
