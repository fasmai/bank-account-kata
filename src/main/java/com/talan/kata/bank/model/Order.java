package com.talan.kata.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;

public class Order {

	private Long clientId;
	private LocalDateTime dateOfOrder;
	private BigDecimal amount;
	
	public Order(Long clientId, LocalDateTime dateOfOrder, BigDecimal amount) {
		this.clientId = clientId;
		this.dateOfOrder = dateOfOrder;
		this.amount = amount;
	}

	public Long getClientId() {
		return clientId;
	}

	public LocalDateTime getDateOfOrder() {
		return dateOfOrder;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	
}
