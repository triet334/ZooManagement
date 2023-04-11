/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Classanimal;
import com.example.ProjectSem4.Entities.Employeeposition;
import com.example.ProjectSem4.IServices.IEmployeePosition;
import com.example.ProjectSem4.Repositories.EmployeePositionRepository;
import com.example.ProjectSem4.IServices.IClassAnimal;
import com.example.ProjectSem4.Repositories.ClassAnimalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ClassAnimalService implements IClassAnimal{

    @Autowired
    ClassAnimalRepository reposeitory;
    @Autowired
    EmployeePositionRepository repository;
    @Override
    public List<Classanimal> getAllClassAnimals() {
        try {
            return reposeitory.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
//	@Override
//    public List<Employeeposition> findAll() {
//        try {
//            return repository.findAll();
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return null;
//    }

//    @Override
//    public Classanimal getOne(int code) {
//        try {
//            return reposeitory.findByCode(code);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return null;
//    }
    
}
