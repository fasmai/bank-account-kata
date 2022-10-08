package com.talan.kata.bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class BankAccount{

	
	private Long id;
	private Long clientId;
	private LocalDateTime creationDate;
	@Getter
	private BigDecimal balance;
	private List<Order> history = new ArrayList<>();


	public BankAccount(Long id, Long clientId, LocalDateTime creationDate, BigDecimal balance) {
		this.id = id;
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.balance = balance;
	}
	

	public void deposite(BigDecimal amount) throws RuntimeException {
		
		if(amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("You can't deposite a negatif amount !");
		}
		
		this.balance = this.balance.add(amount);	
		Order depositeOrder = new Order(this.clientId, LocalDateTime.now(), amount, balance);
		history.add(depositeOrder);
	}

	public void withdraw(BigDecimal amount) throws RuntimeException {
		
		if(amount.compareTo(this.balance) > 0) {
			throw new RuntimeException("Your amount order is greater than your balance !");
		}
		
		this.balance = this.balance.subtract(amount);
		Order withdrawOrder = new Order(this.clientId, LocalDateTime.now(), amount, balance);
		history.add(withdrawOrder);
	}

	public BigDecimal getBalance() {
		return balance;
	}


	public List<Order> getHistory() {
		return this.history;
	}
	
	
}
