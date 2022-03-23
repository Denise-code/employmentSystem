package com.denise.employmentsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="compensation")
public class Compensation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int idCompensation;
	
	
	private String type;
	
	private int amount;
	private String description;
	private String date;
	
	
	public int idEmployee;

	public Compensation() {
	}

	

	public Compensation(int idCompensation, String type, int amount, String description, String date,
			int idEmployee) {
		super();
		this.idCompensation = idCompensation;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.date = date;
		this.idEmployee = idEmployee;
	}



	public int getIdCompesation() {
		return idCompensation;
	}

	public void setIdCompesation(int idCompesation) {
		this.idCompensation = idCompesation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
}
