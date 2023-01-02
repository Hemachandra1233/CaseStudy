package com.financemanagement.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BILLS_TBL")
public class Bills {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "transaction_id")
	private long transactionId;
	
	@Column(name = "original_file_name")
	private String originalFileName;
	
	@Column(name = "file_name")
	private String fileName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Bills(long id, long transactionId, String originalFileName, String fileName) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.originalFileName = originalFileName;
		this.fileName = fileName;
	}

	public Bills() {
		
	}
}
