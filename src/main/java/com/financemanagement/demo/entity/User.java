package com.financemanagement.demo.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER_TBL")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Please enter name")
	public String name;
	
	@Column(unique = true)
	@NotNull(message = "please enter email")
	@Email(message = "please enter valid email")
	private String email;
	
	private Long phoneNo;
	
	@JsonIgnore
	@Size(min= 5, message = "Password should be atlease 5 characters")
	private String password;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updateddAt;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role_tbl_1", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private List<UserRoles> roles;


	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdateddAt() {
		return updateddAt;
	}

	public void setUpdateddAt(Timestamp updateddAt) {
		this.updateddAt = updateddAt;
	}

	public List<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}

	public User(Long id, String name,String email,
			Long phoneNo, String password,
			Timestamp createdAt, Timestamp updateddAt, List<UserRoles> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
		this.createdAt = createdAt;
		this.updateddAt = updateddAt;
		this.roles = roles;
	}

	public User() {
		
	}
	
}
