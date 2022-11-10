package com.masai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.NotFoundException;
import com.masai.exception.UserAlreadyExistWithMobileNumber;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;

import com.masai.repository.UserDao;
import com.masai.repository.SessionDao;

import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

@Service
public class UserServiceImpl implements UserServiceIntr{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;
	
	
	
	@Override
	public User createUser(User user) {
		
	 Optional<User> opt =	userDao.findByMobileNumber(user.getMobileNumber());
	 
	 if(opt.isPresent()) {
		 throw new UserAlreadyExistWithMobileNumber("User already exist with this mobile number..");
	 }
		
		
		return userDao.save(user);
		
	}




	@Override
	public User removeUser(String key) {
		
		User customer = getCurrentLoginUser.getCurrentUser(key);	
	CurrentUserSession	currentUserSession =getCurrentLoginUser.getCurrentUserSession(key);
		userDao.delete(customer);
		
		sessionDao.delete(currentUserSession);
		return customer;
	}

	@Override
	public User getUserDetails(String key) {
		
		User customer = getCurrentLoginUser.getCurrentUser(key);	
//		System.out.println(user);
		return customer;
	}


	@Override
	public List<User> getUserList() throws NotFoundException {
		// TODO Auto-generated method stub
     
		List<User> customers = userDao.findAll();
		
		if(customers.size() <= 0) {
			throw new NotFoundException("Customer not found..");
		}
		return userDao.findAll();
		
		
	}





}
