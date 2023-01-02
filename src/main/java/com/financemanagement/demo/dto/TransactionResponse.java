package com.financemanagement.demo.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.financemanagement.demo.entity.Categories;
import com.financemanagement.demo.entity.SpendTypes;
import com.financemanagement.demo.entity.TransactionType;

public class TransactionResponse {

	private long transactionId;

	private Date transactionDate;

	private Time transactionTime;

	private long amount;

	private String expenseTo;

	private String description;

	private Categories category;

	private SpendTypes spendType;

	private TransactionType transactionType;

	private long homeId;

	private String createdBy;

	private Timestamp createdOn;
	
	private String updatedBy;

	private Timestamp updatedOn;
	
	private String createdOnTime;
	
	private String updatedOnTime;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Time getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getExpenseTo() {
		return expenseTo;
	}

	public void setExpenseTo(String expenseTo) {
		this.expenseTo = expenseTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public SpendTypes getSpendType() {
		return spendType;
	}

	public void setSpendType(SpendTypes spendType) {
		this.spendType = spendType;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public long getHomeId() {
		return homeId;
	}

	public void setHomeId(long homeId) {
		this.homeId = homeId;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedOnTime() {
		return createdOnTime;
	}

	public void setCreatedOnTime(String createdOnTime) {
		this.createdOnTime = createdOnTime;
	}

	public String getUpdatedOnTime() {
		return updatedOnTime;
	}

	public void setUpdatedOnTime(String updatedOnTime) {
		this.updatedOnTime = updatedOnTime;
	}
}
