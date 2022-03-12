package com.nrifintech.mms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Component
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int a_id;
	private String userName;
	private String password;
	private String gender;
	private String phone;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Admin(int a_id, String userName, String password, String gender, String phone) {
		super();
		this.a_id = a_id;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
	}



	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
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



	@Override
	public String toString() {
		return "Admin [a_id=" + a_id + ", userName=" + userName + ", password=" + password + ", gender=" + gender
				+ ", phone=" + phone + "]";
	}
	
	
	
	
}
