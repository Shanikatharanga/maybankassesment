package com.springbatch.maybank.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountNumber;
	private BigDecimal trxAmount;
	private String description;
	
	private LocalDate trxDate;
	private LocalTime trxTime;
	private String customerId;

	public TransactionData() {
		super();
	}

	public TransactionData(Long id, String accountNumber, BigDecimal trxAmount, String description, LocalDate trxDate,
			LocalTime trxTime, String customerId) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.trxAmount = trxAmount;
		this.description = description;
		this.trxDate = trxDate;
		this.trxTime = trxTime;
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getTrxAmount() {
		return trxAmount;
	}

	public void setTrxAmount(BigDecimal trxAmount) {
		this.trxAmount = trxAmount.setScale(3, RoundingMode.HALF_UP);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(String trxDate) {
		this.trxDate = LocalDate.parse(trxDate);
	}

	public LocalTime getTrxTime() {
		return trxTime;
	}

	public void setTrxTime(String trxTime) {
		this.trxTime = LocalTime.parse(trxTime);
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "TransactionData [id=" + id + ", accountNumber=" + accountNumber + ", trxAmount=" + trxAmount
				+ ", description=" + description + ", trxDate=" + trxDate + ", trxTime=" + trxTime + ", customerId="
				+ customerId + "]";
	}

}
