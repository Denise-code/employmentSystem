package com.denise.employmentsystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.denise.employmentsystem.interfaces.IEmployee;
import com.denise.employmentsystem.model.Employee;
import com.denise.employmentsystem.services.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService{

	
	@Autowired
	private IEmployee data;
	
	
	@Override
	public List<Employee> view() {
		return (List<Employee>) data.findAll();
	}

	@Override
	public Optional<Employee> viewId(int idEmployee) {

		
		return data.findById(idEmployee);
	}

	@Override
	public int add(Employee employee) {
		int res=0;
		Employee employee2 = data.save(employee);
		if(!employee2.equals(null)) {
			res=1;
		}
		
		return res;
	}

	@Override
	public void delete(int idEmployee) {
		data.deleteById(idEmployee);;
	}

	@Override
	public List<Employee> buscarFirstName(String firstName) {
		if(data.findByFirstName(firstName) != null) {
			return data.findByFirstName(firstName);
		}else {
			return null;
		}
		
	}

	@Override
	public List<Employee> buscarLastName(String lastName) {
		return data.findByLastName(lastName);

	}

	@Override
	public List<Employee> buscarPosition(String position) {
		return data.findByPosition(position);

	}

	@Override
	public List<Employee> buscarByFirstNameAndLastNameAndPosition(String firstName, String lastName, String position) {
		
		
		return data.findByFirstNameAndLastNameAndPosition(firstName, lastName, position);
	}

	@Override
	public List<Employee> buscarByFirstNameAndLastName(String firstName, String lastName) {
		return data.findByFirstNameAndLastName(firstName, lastName);

	}

	@Override
	public List<Employee> buscarByFirstNameAndPosition(String firstName, String position) {
		return data.findByFirstNameAndPosition(firstName, position);
	}

	@Override
	public List<Employee> buscarByLastNameAndPosition(String lastName, String position) {
		return data.findByLastNameAndPosition(lastName, position);
	}

	@Override
	public List<Employee> buscarByFirstNameAndMiddleNameAndLastNameAndBirthDate(String firstName, String middleName,
			String lastName, String birthDate) {
		return data.findByFirstNameAndMiddleNameAndLastNameAndBirthDate(firstName, middleName, lastName, birthDate);
	}


	
	
	

	
	

	

	
}
