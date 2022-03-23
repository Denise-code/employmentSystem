package com.denise.employmentsystem.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.denise.employmentsystem.model.Compensation;

@Repository
public interface ICompensation  extends CrudRepository<Compensation, Integer> {
	List<Compensation> findByTypeAndDateAndIdEmployee(String type, String date, int idEmployee);
	
	@Query("SELECT cmp FROM Compensation cmp "+ "WHERE cmp.idEmployee = :idEmployee AND cmp.date BETWEEN :date1 AND :date2")
	List<Compensation> findByIdEmployeeAndDate(int idEmployee, String date1, String date2 );
	
	@Query("SELECT cmp FROM Compensation cmp "+ "WHERE cmp.idEmployee = :idEmployee ")
	List<Compensation> searchByIdEmployee(int idEmployee);
	
	@Query("SELECT cmp FROM Compensation cmp "+ "WHERE cmp.idEmployee = :idEmployee AND cmp.date = :date1")
	List<Compensation> findByIdEmployeeAndDate1(int idEmployee, String date1);
	

	List<Compensation> findByIdCompensationAndTypeAndAmountAndDescriptionAndDateAndIdEmployee(int idCompensation, String type, int amount, String description, String date, int idEmployee);
	
	
}
