/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Assignmentforemployeeandcage;
import com.example.ProjectSem4.Entities.Cage;
import com.example.ProjectSem4.Entities.Employee;
import com.example.ProjectSem4.IServices.IAssignmentForEmployeeAndCage;
import com.example.ProjectSem4.Repositories.AssignmentForEmployeeAndCageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AssignmentForEmployeeAndCageService implements IAssignmentForEmployeeAndCage{
    @Autowired
    AssignmentForEmployeeAndCageRepository repository;
    
    @Override
    public List<Assignmentforemployeeandcage> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void saveAssignment(Assignmentforemployeeandcage assignment) {
        try {
            
//            Assignmentforemployeeandcage ass = new Assignmentforemployeeandcage();
//        
//            Cage cage = new Cage();
//            cage.setCageCode(assignment.getCageCode().getCageCode());
//
//            Employee emp = new Employee();
//            emp.setEmployeeCode(assignment.getEmployeeCode().getEmployeeCode());
//
//            ass.setCageCode(cage);
//            ass.setEmployeeCode(emp);
//
//             repository.save(ass);
             repository.save(assignment);
        } catch (Exception e) {
            e.getMessage();
        }        
    }
    
    @Override
    public void removeAssignment(int assCode) {
        try {
            repository.deleteById(assCode);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public Assignmentforemployeeandcage getOne(int assignmentCode) {
        try {
            return repository.findOne(assignmentCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

//    @Override
//    public void updateAssignment(Assignmentforemployeeandcage assignment) {
//        try {
//            Assignmentforemployeeandcage ass = new Assignmentforemployeeandcage();
//            ass.setAssignmentCode(assignment.getAssignmentCode());
//            Cage cage = new Cage();
//            cage.setCageCode(assignment.getCageCode().getCageCode());
//
//            Employee e = new Employee();
//            e.setEmployeeCode(assignment.getEmployeeCode().getEmployeeCode());
//
//            ass.setCageCode(cage);
//            ass.setEmployeeCode(e);
//
//            repository.save(ass);
//        } catch (Exception e) {
//            e.getMessage();
//        }      
//    }

    @Override
    public List<Assignmentforemployeeandcage> findByEmp(String code) {
        try {
            return repository.findbyEmp(code);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
@Override
    public Assignmentforemployeeandcage checkCageAndEmployee(int cageCode, String employeeCode) {
        try {
            return repository.checkCageAndEmployee(cageCode, employeeCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    @Override
    public int countEmployeeForCage(int code) {
        try {
            int count = repository.countEmployeeForCage(code);
            return count;
        } catch (Exception e) {
            e.getMessage();
        }
        return 3;
    }

    @Override
    public int checkEmployeeFullCages(String code) {
         try {
            int count = repository.checkEmployeeFullCages(code);
            return count;
        } catch (Exception e) {
            e.getMessage();
        }
        return 0;
    }

//    @Override
//    public List<Employee> findEmpByCagecode(int code) {
//       return repository.findEmployeeByCage(code);
//    }

    @Override
    public List<Assignmentforemployeeandcage> findEmpByCode(String code) {
        try {
            return repository.findEmpByCode(code);
            
        } catch (Exception e) {
            e.getMessage();
            return null;
        }      
    }

    @Override
    public List<Assignmentforemployeeandcage> findEmployee(int cageCode) {
       try {
            return repository.findEmployee(cageCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Assignmentforemployeeandcage> findByCage(int code) {
        try {
            return repository.findbyCa(code);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
}
