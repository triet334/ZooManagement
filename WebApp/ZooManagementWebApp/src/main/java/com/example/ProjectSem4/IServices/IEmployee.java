/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.DTO.EmployeeDTO;
import com.example.ProjectSem4.Entities.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
public interface IEmployee {
    public List<Employee> findAll();
    
    public Employee findOneEmp(String employeeCode);
                
    public boolean createEmployee(EmployeeDTO employeeDTO);
      
    public boolean updateEmployee(EmployeeDTO employeeDTO);
    
    public boolean updatePass(String code,String password);
       
    public Employee checkLogin(String code,String password);
      
    public List<Employee> searchCode(String code);
    
    public List<Employee> searchName(String name);        
    
    public boolean deleteEmployee(String code);
    
    public boolean resetPassword(String code);

    public boolean codeExist(String employeeCode);

    public List<Employee> findAllExcept();
	public boolean updateAPI(Employee eapi);

}
