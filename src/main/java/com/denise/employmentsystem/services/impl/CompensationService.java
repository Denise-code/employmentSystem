package com.denise.employmentsystem.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denise.employmentsystem.interfaces.ICompensation;
import com.denise.employmentsystem.model.Compensation;
import com.denise.employmentsystem.services.ICompensationService;

@Service
public class CompensationService implements ICompensationService {
	@Autowired
	private ICompensation data;

	@Override
	public int add(Compensation compensation) {
		int res=0;
		Compensation comp = data.save(compensation);
		if(!comp.equals(null)) {
			res=1;
		}
		
		return res;
	}

	@Override
	public List<Compensation> viewAll() {
		return (List<Compensation>) data.findAll();
	}

	@Override
	public List<Compensation> buscarByTypeAndDateAndIdEmployee(String type, String date, int idEmployee) {
		return (List<Compensation>) data.findByTypeAndDateAndIdEmployee(type, date, idEmployee);
	}

	@Override
	public List<Compensation> buscarByIdEmployeeAndDate(int idEmployee, String date1, String date2) {
		return (List<Compensation>) data.findByIdEmployeeAndDate(idEmployee, date1, date2);
	}

	@Override
	public List<Compensation> buscarByIdEmployee(int idEmployee) {
		return (List<Compensation>)  data.searchByIdEmployee(idEmployee);
	}

	@Override
	public List<Compensation> buscarByIdEmployeeAndDate1(int idEmployee, String date1) {
		return data.findByIdEmployeeAndDate1(idEmployee, date1);
	}

	@Override
	public Optional<Compensation> viewId(int idCompensation) {
		return data.findById(idCompensation);
	}

	@Override
	public List<Compensation> searchByIdCompensationAndTypeAndAmountAndDescriptionAndDateAndIdEmployee(
			int idCompensation, String type, int amount, String description, String date, int idEmployee) {
		return data.findByIdCompensationAndTypeAndAmountAndDescriptionAndDateAndIdEmployee(idCompensation, type, amount, description, date, idEmployee);
	}

	@Override
	public void deleted(int idCompensation) {
		data.deleteById(idCompensation);
	}

	
	

	
}
