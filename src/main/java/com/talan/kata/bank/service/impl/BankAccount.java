package com.talan.kata.bank.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.talan.kata.bank.business.Order;
import com.talan.kata.bank.model.Client;
import com.talan.kata.bank.service.BankAccountService;

public class BankAccount implements BankAccountService{

	private long id;
	private Client client;
	private Date date;// la date de quoi
	private double amount; // BigDecimen is better
	private int balance;
	List<Order> history = new ArrayList<>();


	public BankAccount(long id, Client client, Date date, double amount, int balance) {
		//super();
		this.id = id;
		this.client = client;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}
	

	@Override
	public void deposit(double amount) {
			this.balance += amount;	
			// construct order
			//history.add(order1);
	}

	@Override
	public void withdraw(double amount) {
			this.balance -= amount;	
	}

	@Override
	public void printStatement() {
		this.getHistory();		
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public List<Order> getHistory() {
		return history;
	}

	public void setHistory(List<Order> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", client=" + client + ", date=" + date + ", amount=" + amount + ", balance="
				+ balance + ", history=" + history + "]";
	}


	public void order(Order order1) {
		
		
		if(order1.getName().equals("Deposite")) {
			this.deposit(order1.getAmount());
		}else if(order1.getName().equals("Withdraw")){
			this.withdraw(order1.getAmount());
		}
		
	}
	
	
}
