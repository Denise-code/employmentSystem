package com.denise.employmentsystem.model;


public class CompensationMonth {

	
	private String month;
	private double amount;
	private String mounths;
	private int years;
	public CompensationMonth(String month, double amount,String mounths, int years) {
		super();
		this.month = month;
		this.amount = amount;
		this.mounths = mounths;
		this.years = years;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void addAmount(double amount) {
		this.amount += amount;
	}
	
	public String getMounths() {
		return mounths;
	}
	public void setMounths(String mounths) {
		this.mounths = mounths;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	
	
	
}
