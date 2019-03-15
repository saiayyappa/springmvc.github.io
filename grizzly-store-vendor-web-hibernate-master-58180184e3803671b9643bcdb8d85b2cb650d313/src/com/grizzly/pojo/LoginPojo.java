package com.grizzly.pojo;

public class LoginPojo {

	private String userName;
	private String password;
	private String role;
	private boolean accStatus; 
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isAccStatus() {
		return accStatus;
	}
	public void setAccStatus(boolean accStatus) {
		this.accStatus = accStatus;
	}
	
}
