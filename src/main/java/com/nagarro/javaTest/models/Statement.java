package com.nagarro.javaTest.models;

public class Statement {

	private int id;
	private String accountId;
	private String datefield;
	private String amount;
	
	public Statement(){}
	
	public Statement(int id,String accountId,String datefield,String amount){
		this.id=id;
		this.accountId=accountId;
		this.datefield=datefield;
		this.amount=amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String string) {
		this.accountId = string;
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
