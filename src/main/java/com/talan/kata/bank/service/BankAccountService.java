package com.talan.kata.bank.service;

public interface BankAccountService {
	
		public void deposit(double amount) ;
		public void withdraw(double amount);
		public void printStatement();
}
