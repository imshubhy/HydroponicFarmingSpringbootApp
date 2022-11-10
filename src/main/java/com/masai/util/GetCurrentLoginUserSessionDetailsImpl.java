package com.masai.util;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.NotFoundException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;

import com.masai.repository.UserDao;
import com.masai.repository.SessionDao;

@Service
public class GetCurrentLoginUserSessionDetailsImpl implements GetCurrentLoginUserSessionDetailsIntr{
   
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private UserDao customerDao;

	public CurrentUserSession getCurrentUserSession(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new NotFoundException("Unauthorized Key");
		}
		
		return optional.get();
	}
	

	
	public User getCurrentUser(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		if(!optional.isPresent()) {
			throw new NotFoundException("Unauthorized Key");
		}
		
		Integer customerId = optional.get().getUserId();
		
		return  customerDao.getById(customerId);
	}
	

	

	
	  
	
	

}
