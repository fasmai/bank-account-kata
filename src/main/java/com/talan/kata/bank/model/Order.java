package com.talan.kata.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;

public class Order {

	private Long clientId;
	private LocalDateTime dateOfOrder;
	private BigDecimal amount;
	private BigDecimal balance;

	
	public Order(Long clientId, LocalDateTime dateOfOrder, BigDecimal amount, BigDecimal balance) {
		this.clientId = clientId;
		this.dateOfOrder = dateOfOrder;
		this.amount = amount;
		this.balance = balance;
	}

	public Long getClientId() {
		return clientId;
	}

	public LocalDateTime getDateOfOrder() {
		return dateOfOrder;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public BigDecimal getBalance(){
		return balance;
	}
}
