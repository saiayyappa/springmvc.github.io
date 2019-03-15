package com.grizzly.entity;

import javax.persistence.*;

@Entity
@Table(name="LoginEntity")
public class LoginEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="Username")
	private String userName;
	
	@Column(name="Login_Password")
	private String password;
	
	@Column(name="Role")
	private String role;
	
	@Column(name="Acc_Status")
	private boolean accStatus;
	
	public boolean getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(Boolean accStatus) {
		this.accStatus = accStatus;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

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
	
	public LoginEntity()
	{
		
	}
}
