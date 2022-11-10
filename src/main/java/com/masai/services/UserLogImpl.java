package com.masai.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.InvalidPasswordException;
import com.masai.exception.NotFoundException;
import com.masai.exception.UserAlreadyExistWithMobileNumber;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.model.LogDetails;
import com.masai.model.LoginDTO;
import com.masai.repository.UserDao;
import com.masai.repository.LogDetailsDao;
import com.masai.repository.SessionDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsIntr;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLogImpl implements UserLogIntr{
   
	@Autowired
	private UserDao customerDao;
	@Autowired
	private  SessionDao sessionDao;
	@Autowired
	private GetCurrentLoginUserSessionDetailsIntr getCurrentLoginUser;
	
	@Autowired
	private LogDetailsDao logDetailsDao;
	
	
	@Override
	public String logIntoAccount(LoginDTO customerDTO) {
		

		Optional<User> opt = customerDao.findByMobileNumber(customerDTO.getMobileNumber());
		
		
		if(!opt.isPresent()) {
			throw new NotFoundException("Please Enter Valid Mobile Number");
		}
		
		   User newCustomer = opt.get();
		   
		  Integer customerId = newCustomer.getUserId();
		
		Optional<CurrentUserSession> currentUserOptional = sessionDao.findByUserId(customerId);
		
		if(currentUserOptional.isPresent()) {
			throw new UserAlreadyExistWithMobileNumber("User already logged in with this number");
		}
		
		if(newCustomer.getPassword().equals(customerDTO.getPassword())) {
			
			String key = RandomString.make(7);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(newCustomer.getUserId(), key, LocalDateTime.now());			
			sessionDao.save(currentUserSession);
			   LogDetails logD = new LogDetails();
			   
			   logD.setCid(currentUserSession.getId());
			   logD.setLocalDateTime(currentUserSession.getLocalDateTime());
			   logD.setLogtype("Log In");
			   logD.setUuid(currentUserSession.getUuid());
			   logDetailsDao.save(logD);
			

			return currentUserSession.toString();
		}
		else {
			throw new InvalidPasswordException("Please Enter Valid Password");
		}
		
	}

	@Override
	public String logOutFromAccount(String key) {
		
		
  Optional<CurrentUserSession> currentUserOptional = sessionDao.findByUuid(key);
		
		if(!currentUserOptional.isPresent()) {
			throw new NotFoundException("User is not logged in with this number");
		}
		
		CurrentUserSession currentUserSession = getCurrentLoginUser.getCurrentUserSession(key);
		
		   LogDetails logD = new LogDetails();
		   
		   logD.setCid(currentUserSession.getId());
		   logD.setLocalDateTime(currentUserSession.getLocalDateTime().now());
		   logD.setLogtype("Log Out");
		   logD.setUuid(currentUserSession.getUuid());
		   logDetailsDao.save(logD);
		
		
		  sessionDao.delete(currentUserSession);
		
		return "Logged Out...";
	}

}
