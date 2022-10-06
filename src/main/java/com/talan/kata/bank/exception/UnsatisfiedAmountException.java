package com.talan.kata.bank.exception;

public class UnsatisfiedAmountException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnsatisfiedAmountException(String errorMessage) {
	        super(errorMessage);
	    }
}
