package com.financemanagement.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "CATEGORIES_TBL")
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String description;
	private long userId;
	private String createdBy;
	@CreationTimestamp
	private Timestamp createdOn;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Categories(long id, String description, long userId, String createdBy, Timestamp createdOn)
	{
		super();
		this.id = id;
		this.description = description;
		this.userId = userId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	public Categories() {
		
	}
}
