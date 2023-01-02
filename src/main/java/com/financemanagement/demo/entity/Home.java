package com.financemanagement.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "HOME_TBL")
@SQLDelete(sql = "UPDATE HOME_TBL SET status = -1 WHERE id=?")
@Where(clause = "status=1")
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String description;
	@CreationTimestamp
	private Timestamp createdOn;
	private String createdBy;
	
	@UpdateTimestamp
	private Timestamp updateddOn;
	
	private Long status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

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

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdateddOn() {
		return updateddOn;
	}

	public void setUpdateddOn(Timestamp updateddOn) {
		this.updateddOn = updateddOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	
	public Home(long id, String description, Timestamp createdOn, String createdBy, Timestamp updateddOn,
			Long status, User user) {
		super();
		this.id = id;
		this.description = description;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updateddOn = updateddOn;
		this.status = status;
		this.user = user;
	}

	public Home() {
		
	}
}
