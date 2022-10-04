package com.talan.kata.bank.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.talan.kata.bank.exception.UnsatisfiedAmountException;
import com.talan.kata.bank.model.Order;

public class BankAccount{

	private Long id;
	private Long clientId;
	private LocalDateTime creationDate;// la date de quoi
	private BigDecimal amount; // BigDecimen is better
	private BigDecimal balance;
	List<Order> history = new ArrayList<>();


	public BankAccount(Long id, Long clientId, LocalDateTime creationDate, BigDecimal amount, BigDecimal balance) {
		this.id = id;
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.amount = amount;
		this.balance = balance;
	}
	

	public void deposite(BigDecimal amount, LocalDate date) throws UnsatisfiedAmountException {
		
		if(amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new UnsatisfiedAmountException("You can't deposite a negatif amount !");
		}
		
		this.balance = this.balance.add(amount);	
		Order currentOrder = new Order(this.clientId, date, amount);
		history.add(currentOrder);
	}

	public void withdraw(BigDecimal amount, LocalDate date) throws UnsatisfiedAmountException {
		
		if(amount.compareTo(this.balance) > 0) {
			throw new UnsatisfiedAmountException("Your amount order is greater than your balance !");
		}
		
		this.balance = this.balance.subtract(amount);
		Order currentOrder = new Order(this.clientId, date, amount);
		history.add(currentOrder);
	}

	public List<Order> printStatement() {
		return this.history;
	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	
}
