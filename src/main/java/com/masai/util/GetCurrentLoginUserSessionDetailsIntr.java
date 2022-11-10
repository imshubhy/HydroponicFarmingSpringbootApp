package com.masai.util;


import com.masai.exception.NotFoundException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;


public interface GetCurrentLoginUserSessionDetailsIntr {
	
   public CurrentUserSession getCurrentUserSession(String key) throws NotFoundException;
	
	
	public User getCurrentUser(String key) throws NotFoundException;
	
	
}
