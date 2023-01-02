package com.financemanagement.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "USER_HOME_TBL")
public class UserHome {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long assigneeId;
	private long homeId;
	private long roleId;
	private long assignerId;

	@CreationTimestamp
	private Date createdOn;

	@UpdateTimestamp
	private Date updatedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public long getHomeId() {
		return homeId;
	}

	public void setHomeId(long homeId) {
		this.homeId = homeId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getAssignerId() {
		return assignerId;
	}

	public void setAssignerId(long assignerId) {
		this.assignerId = assignerId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public UserHome(long id, long assigneeId, long homeId, long roleId, long assignerId, Date createdOn,
			Date updatedOn) {
		super();
		this.id = id;
		this.assigneeId = assigneeId;
		this.homeId = homeId;
		this.roleId = roleId;
		this.assignerId = assignerId;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public UserHome() {

	}

}
