package com.grizzly.service;
import com.grizzly.dao.LoginDaoImpl;
import com.grizzly.exception.ApplicationException;
import com.grizzly.pojo.LoginPojo;

public class LoginServiceImpl implements LoginService {

	public String checkUser(LoginPojo login) throws ApplicationException
	{
		LoginDaoImpl userLogin = new LoginDaoImpl();
		String check = userLogin.checkUser(login);
		return check;
	}
	
	public int getId(String userName, String password) throws ApplicationException
	{
		LoginDaoImpl userLogin = new LoginDaoImpl();
		int id = userLogin.getId(userName, password);
		return id;
	}
	
}
