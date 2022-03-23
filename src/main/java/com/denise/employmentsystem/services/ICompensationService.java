package com.denise.employmentsystem.services;

import java.util.List;
import java.util.Optional;

import com.denise.employmentsystem.model.Compensation;

public interface ICompensationService {
	public List<Compensation> viewAll();
	public int add(Compensation compensation);
	public List<Compensation> buscarByTypeAndDateAndIdEmployee(String type, String date, int idEmployee);
	public List<Compensation> buscarByIdEmployeeAndDate(int idEmployee, String date1, String date2 );
	public List<Compensation> buscarByIdEmployee(int idEmployee);

	public List<Compensation> buscarByIdEmployeeAndDate1(int idEmployee, String date1);
	
	public Optional<Compensation>viewId(int idCompensation);
	
	List<Compensation> searchByIdCompensationAndTypeAndAmountAndDescriptionAndDateAndIdEmployee(int idCompensation, String type, int amount, String description, String date, int idEmployee);

	public void deleted(int idCompensation);
}
