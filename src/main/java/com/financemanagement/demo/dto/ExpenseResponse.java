package com.financemanagement.demo.dto;

import java.sql.Date;
import java.sql.Time;

public class ExpenseResponse {

	private Date Date;
	private Time Time;
	private long Amount;
	private String Category;
	private String SpendType;
	private String transactionType;
	private String createdBy;
	
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public Time getTime() {
		return Time;
	}
	public void setTime(Time time) {
		Time = time;
	}
	public long getAmount() {
		return Amount;
	}
	public void setAmount(long amount) {
		Amount = amount;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getSpendType() {
		return SpendType;
	}
	public void setSpendType(String spendType) {
		SpendType = spendType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
}
