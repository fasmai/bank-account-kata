package com.talan.kata.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
public class Order {

	private Long clientId;
	private LocalDateTime  dateOfOrder;
	private BigDecimal amount; 	
	
}
