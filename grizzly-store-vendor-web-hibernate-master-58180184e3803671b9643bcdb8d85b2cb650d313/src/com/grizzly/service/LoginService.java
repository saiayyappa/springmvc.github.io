package com.grizzly.service;
import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.LoginPojo;

public interface LoginService {
	
	 String checkUser(LoginPojo login) throws ApplicationException;
	
	 int getId(String userName, String password) throws ApplicationException;
	
}
