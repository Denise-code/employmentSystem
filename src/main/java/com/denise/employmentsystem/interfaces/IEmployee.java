package com.denise.employmentsystem.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.denise.employmentsystem.model.Employee;

@Repository
public interface IEmployee extends CrudRepository<Employee, Integer> {
	

	//findByFirstName
	List<Employee> findByFirstName(String firstName);
	
	//findByLastName
	List<Employee> findByLastName(String lastName);
	
	//findByPosition
	List<Employee> findByPosition(String position);
		
	//findByFirstNameAndLastNameAndPosition
	List<Employee> findByFirstNameAndLastNameAndPosition(String firstName, String lastName, String position);
	
	//findByFirstNameAndLastName
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
		
	//findByFirstNameAndPosition
    List<Employee> findByFirstNameAndPosition(String firstName, String position);
		
	//findByLastNameAndPosition
    List<Employee> findByLastNameAndPosition(String lastName, String position);
		
  //findByFirstNameAndMiddleNameAndLastNameAndBirthDate
  	List<Employee> findByFirstNameAndMiddleNameAndLastNameAndBirthDate(String firstName, String middleName,String lastName, String birthDate);


	
}
