package com.financemanagement.demo.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
//import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financemanagement.demo.dto.ResponseByYear;

@Entity
@Table(name = "TRANSACTIONS_TBL")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;

	private Date transactionDate;

	private Time transactionTime;

	private long amount;

	private String expenseTo;

	private String description;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

	@JoinColumn(name = "category_id", unique = false)
	private Categories category;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)

	@JoinColumn(name = "spend_type_id", unique = false)
	private SpendTypes spendType;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_type_id", unique = false)
	private TransactionType transactionType;

	private long homeId;

	private String createdBy;
	@CreationTimestamp
	private Timestamp createdOn;
	private String updatedBy;

	@UpdateTimestamp
	private Timestamp updatedOn;

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

	public Transactions(long transactionId, Date transactionDate, Time transactionTime, long amount, String description,
			Categories category, SpendTypes spendType, TransactionType transactionType, long homeId, String createdBy,
			Timestamp createdOn, String updatedBy, Timestamp updatedOn, String expenseTo) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.description = description;
		this.category = category;
		this.spendType = spendType;
		this.transactionType = transactionType;
		this.homeId = homeId;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.expenseTo = expenseTo;
	}

	public Transactions() {

	}
}