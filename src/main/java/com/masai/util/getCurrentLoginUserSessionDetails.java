package com.masai.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.repository.UserDao;
import com.masai.repository.SessionDao;

@Service
public class getCurrentLoginUserSessionDetails {

	
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private UserDao customerDao;
	
	public CurrentUserSession getCurrentUserSession(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		return optional.get();
	}
	
	public Integer getCurrentUserSessionId(String key) {
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		return optional.get().getId();
	}
	
	public User getCurrentCustomer(String key) {
		
		Optional<CurrentUserSession> optional = sessionDao.findByUuid(key);
		
		Integer customerId = optional.get().getUserId();
		
		return  customerDao.getById(customerId);
	}
}
