package com.financemanagement.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPEND_TYPES_TBL")
public class SpendTypes {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "type")
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SpendTypes(long id, String description, String type) {
		super();
		this.id = id;
		this.description = description;
		this.type = type;
	}
	
	public SpendTypes() {
		
	}
	
}
