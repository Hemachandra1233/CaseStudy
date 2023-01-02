package com.financemanagement.demo.dto;

import java.sql.Date;
import java.sql.Time;

public class TransactionRequest {

	private Date transactionDate;
	private Time transactionTime;
	private String description;
	private long amount;
	private String expenseTo;
	private long categoryId;
	private long spendTypeId;
	private long transactionTypeId;
	private long homeId;
	private String createdBy;
	
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
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getSpendTypeId() {
		return spendTypeId;
	}
	public void setSpendTypeId(long spendTypeId) {
		this.spendTypeId = spendTypeId;
	}
	public long getTransactionTypeId() {
		return transactionTypeId;
	}
	public void setTransactionTypeId(long transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}
	public long getHomeId() {
		return homeId;
	}
	public void setHomeId(long homeId) {
		this.homeId = homeId;
	}
	public String getcreatedBy() {
		return createdBy;
	}
	public void setcreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpenseTo() {
		return expenseTo;
	}
	public void setExpenseTo(String expenseTo) {
		this.expenseTo = expenseTo;
	}
	
}
