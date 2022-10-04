package com.talan.kata.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {

	private Long clientId;
	private LocalDate dateOfOrder;
	private BigDecimal amount; 
	
	public Order(Long clientId, LocalDate dateOfOrder, BigDecimal amount) {
		this.clientId = clientId;
		this.dateOfOrder = dateOfOrder;
		this.amount = amount;
	}
	
	
	
}
