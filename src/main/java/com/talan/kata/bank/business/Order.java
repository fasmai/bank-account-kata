package com.talan.kata.bank.business;

import java.util.Date;

public class Order {

	private long id;
	private String name; // transaction type, ENUM is better
	private Date date; // LocalDate is better
	private double amount; // BigDecimal
	// add column current balance, à voir
	
	
	public Order(long id, String name, Date date, double amount) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.amount = amount;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Operation [id=" + id + ", name=" + name + ", date=" + date + ", amount=" + amount + "]";
	}
		
	
}
