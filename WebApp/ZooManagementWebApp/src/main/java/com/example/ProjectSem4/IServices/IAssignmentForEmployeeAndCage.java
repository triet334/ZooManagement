/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.Entities.Assignmentforemployeeandcage;
import com.example.ProjectSem4.Entities.Employee;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IAssignmentForEmployeeAndCage {
   
    public List<Assignmentforemployeeandcage> findAll();

    public List<Assignmentforemployeeandcage> findByEmp(String code);
    
    public void saveAssignment(Assignmentforemployeeandcage assignment);

   
    public void removeAssignment(int id);

    public Assignmentforemployeeandcage getOne(int assignmentCode);

//    public void updateAssignment(Assignmentforemployeeandcage assignment);
    public Assignmentforemployeeandcage checkCageAndEmployee(int cageCode, String employeeCode);

    public int countEmployeeForCage(int code);

    public int checkEmployeeFullCages(String code);

//    public List<Employee> findEmpByCagecode(int code);

    public List<Assignmentforemployeeandcage> findEmpByCode(String code);

    public List<Assignmentforemployeeandcage> findEmployee(int cageCode);
	
	//test
    public List<Assignmentforemployeeandcage> findByCage(int code);
}
