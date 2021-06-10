package com.nagarro.javaTest.models;

public class Statement {

	private int id;
	private String account_id;
	private String datefield;
	private String amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String string) {
		this.account_id = string;
	}
	public String getDatefield() {
		return datefield;
	}
	public void setDatefield(String datefield) {
		this.datefield = datefield;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
