package com.masai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.exception.NotFoundException;
import com.masai.model.User;

@Service
public interface UserServiceIntr {
	
    public User createUser(User user);

	
	public User removeUser(String key) throws NotFoundException;
	
	public User getUserDetails(String key) throws NotFoundException;
	
    public List<User> getUserList() throws NotFoundException;
    
  
	
	
}
