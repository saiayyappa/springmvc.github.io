package com.grizzly.dao;

import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.LoginPojo;

public interface LoginDao {


	 String checkUser(LoginPojo login) throws ApplicationException;
	 
	 int getId(String userName, String password) throws ApplicationException;
	
}
