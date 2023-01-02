package com.financemanagement.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLES_TBL")
public class UserRoles {

	@Id
	private long id;
	
	@Column(name = "description")
	private String desc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public UserRoles(long id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}
	
	public UserRoles() {
		
	}
	

}
