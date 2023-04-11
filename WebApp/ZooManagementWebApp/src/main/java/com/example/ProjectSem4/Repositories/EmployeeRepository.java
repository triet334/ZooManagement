/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    
    @Query("SELECT e FROM Employee e WHERE e.employeeCode = :employeeCode")     
    Employee findByCode(@PathVariable("employeeCode") String employeeCode);
        
    @Query("SELECT e FROM Employee e WHERE e.employeeCode = :employeeCode AND e.password = :password")     
    Employee checkLogin(@PathVariable("employeeCode") String employeeCode,@PathVariable("password") String password);
    
    @Query("SELECT e FROM Employee e WHERE e.employeeCode LIKE :employeeCode")     
    List<Employee> searchByCode(@PathVariable("employeeCode") String employeeCode);
    
    @Query("SELECT e FROM Employee e WHERE e.employeeName LIKE :employeeName")     
    List<Employee> searchByName(@PathVariable("employeeName") String employeeName );   
    
    @Query("SELECT e FROM Employee e WHERE e.positionCode NOT IN(1,3)")     
    List<Employee> findAllExcept();   
    
}
