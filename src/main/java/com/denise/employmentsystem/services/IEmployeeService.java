package com.denise.employmentsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import com.denise.employmentsystem.model.Employee;

public interface IEmployeeService {

	public List<Employee> view();
	public Optional<Employee>viewId(int idEmployee);
	public int add(Employee employee);
	public void delete(int idEmployee);
	public List<Employee> buscarFirstName(String firstName);
	public List<Employee> buscarLastName(String lastName);

	public List<Employee> buscarPosition(String position);
	public List<Employee> buscarByFirstNameAndLastNameAndPosition(String firstName, String lastName, String position);
	public List<Employee> buscarByFirstNameAndLastName(String firstName, String lastName);
	public List<Employee> buscarByFirstNameAndPosition(String firstName, String position);
	public List<Employee> buscarByLastNameAndPosition(String lastName, String position);

	public List<Employee> buscarByFirstNameAndMiddleNameAndLastNameAndBirthDate(String firstName, String middleName,String lastName, String birthDate);

}
