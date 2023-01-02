package com.financemanagement.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	
	@NotBlank(message = "Please enter name")
	public String name;

	@NotNull(message = "please enter email")
	@Email(message = "please enter valid email")
	private String email;
	
	private Long phoneNo;
	
	@NotNull
	@Size(min= 5, message = "Password should be atlease 5 characters")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
