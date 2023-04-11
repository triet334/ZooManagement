/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Assignmentforemployeeandcage;
import com.example.ProjectSem4.Entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface AssignmentForEmployeeAndCageRepository extends JpaRepository<Assignmentforemployeeandcage, Integer> {

    @Query("SELECT a FROM Assignmentforemployeeandcage a WHERE a.assignmentCode = :assignmentCode")
    Assignmentforemployeeandcage findOne(@PathVariable("assignmentCode") int assignmentCode);

    @Query(value = "SELECT * FROM Assignmentforemployeeandcage WHERE employeeCode = :employeeCode", nativeQuery = true)
    List<Assignmentforemployeeandcage> findbyEmp(@PathVariable("employeeCode") String employeeCode);

    @Query(value = "Select * from Assignmentforemployeeandcage where cageCode = :cageCode and employeeCode = :employeeCode", nativeQuery = true)
    Assignmentforemployeeandcage checkCageAndEmployee(@PathVariable("cageCode") int cageCode,
            @PathVariable("employeeCode") String employeeCode);

    @Query(value = "Select Count(cageCode) from Assignmentforemployeeandcage where cageCode = :code", nativeQuery = true)
    public int countEmployeeForCage(@PathVariable("code") int code);

    @Query(value = "select Count(employeeCode) from assignmentforemployeeandcage where employeeCode = :code", nativeQuery = true)
    public int checkEmployeeFullCages(@PathVariable("code") String code);
    
//    @Query(value = "SELECT * FROM Assignmentforemployeeandcage WHERE cageCode = :cageCode", nativeQuery = true)
//    List<Employee> findEmployeeByCage(@PathVariable("cageCode") int cageCode);
    
    @Query(value = "SELECT * FROM Assignmentforemployeeandcage WHERE employeeCode = :employeeCode", nativeQuery = true)
    List<Assignmentforemployeeandcage> findEmpByCode(@PathVariable("employeeCode") String employeeCode);

        @Query(value = "Select * from Assignmentforemployeeandcage where cageCode = :cageCode", nativeQuery = true)
    List<Assignmentforemployeeandcage> findEmployee(@PathVariable("cageCode") int cageCode);
	
	//test
    @Query(value = "SELECT * FROM Assignmentforemployeeandcage WHERE cageCode = :cageCode", nativeQuery = true)
    List<Assignmentforemployeeandcage> findbyCa(@PathVariable("cageCode") int cageCode);
}
