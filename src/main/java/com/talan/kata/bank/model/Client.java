package com.talan.kata.bank.model;

public class Client{

	private long id;

	
	public Client(long id) {
		super();
		this.id = id;		
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + "]";
	}
	
	
}
