/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Employeeposition;
import org.springframework.stereotype.Service;
import com.example.ProjectSem4.IServices.IEmployeePosition;
import com.example.ProjectSem4.Repositories.EmployeePositionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
@Service
public class EmployeePositionService implements IEmployeePosition{

   @Autowired
    EmployeePositionRepository repository;
    
    @Override
    public List<Employeeposition> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Employeeposition> find() {
        try {
            return repository.find();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
