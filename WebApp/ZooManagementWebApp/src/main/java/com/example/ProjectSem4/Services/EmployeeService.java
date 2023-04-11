/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.DTO.EmployeeDTO;
import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.Entities.Employeeposition;
import com.example.ProjectSem4.IServices.IEmployee;
import com.example.ProjectSem4.Repositories.EmployeeRepository;
import com.example.ProjectSem4.Security.EncryptDecrypt;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class EmployeeService implements IEmployee{

    @Autowired
    EmployeeRepository repository;
    
    @Override
    public List<Employee> findAll() {
         try {
            return repository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public Employee findOneEmp(String employeeCode) {
        try {
            return repository.findByCode(employeeCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean createEmployee(EmployeeDTO employeeDTO) {
         try {
            MultipartFile file = employeeDTO.getPhoto();
            byte[] filebyte=file.getBytes();
            String base64="";
            
            base64=Base64.getEncoder().encodeToString(filebyte);
            
            Employeeposition employeePosition= new Employeeposition();
            employeePosition.setPositionCode(employeeDTO.getPositionCode());
            
            Classanimal classanimal = new Classanimal();
            classanimal.setClassCode(employeeDTO.getClassCode());
                       
            String passwordEncrypt= EncryptDecrypt.Encrypt(employeeDTO.getPassword());
            
            Employee employee = new Employee();
            employee.setEmployeeCode(employeeDTO.getEmployeeCode());
            employee.setEmployeeName(employeeDTO.getEmployeeName());
            employee.setBirthday(employeeDTO.getBirthday());
            employee.setGender(employeeDTO.getGender());
            employee.setAddress(employeeDTO.getAddress());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setPositionCode(employeePosition);
            employee.setStatus(true);
            employee.setEmail(employeeDTO.getEmail());
            employee.setPassword(passwordEncrypt);
            employee.setClassCode(classanimal);
            employee.setActivated(true);
            employee.setPhoto(base64);
            
            repository.save(employee);
            
            return true;
            
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
         if (!employeeDTO.getPhoto().isEmpty()) {
            try {
            Employee emp = repository.findByCode(employeeDTO.getEmployeeCode());
            MultipartFile file = employeeDTO.getPhoto();
            byte[] filebyte=file.getBytes();
            String base64="";
            
            base64=Base64.getEncoder().encodeToString(filebyte);
            
            Employeeposition employeePosition= new Employeeposition();
            employeePosition.setPositionCode(employeeDTO.getPositionCode());
            
            Classanimal classanimal = new Classanimal();
            classanimal.setClassCode(employeeDTO.getClassCode());                       
            
            Employee employee = new Employee();
            employee.setEmployeeCode(employeeDTO.getEmployeeCode());
            employee.setEmployeeName(employeeDTO.getEmployeeName());
            employee.setBirthday(employeeDTO.getBirthday());
            employee.setGender(employeeDTO.getGender());
            employee.setAddress(employeeDTO.getAddress());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setPositionCode(employeePosition);
            employee.setStatus(employeeDTO.getStatus());
            employee.setEmail(employeeDTO.getEmail()); 
            employee.setPassword(emp.getPassword());
            employee.setClassCode(classanimal);
            employee.setActivated(employeeDTO.getActivated());
            employee.setPhoto(base64);
            
            repository.save(employee);
            
            return true;
            
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
        
        }
        else{
            try {
            Employee emp = repository.findByCode(employeeDTO.getEmployeeCode());
            
            Employeeposition employeePosition= new Employeeposition();
            employeePosition.setPositionCode(employeeDTO.getPositionCode());
            
            Classanimal classanimal = new Classanimal();
            classanimal.setClassCode(employeeDTO.getClassCode());                        
            
            Employee employee = new Employee();
            employee.setEmployeeCode(employeeDTO.getEmployeeCode());
            employee.setEmployeeName(employeeDTO.getEmployeeName());
            employee.setBirthday(employeeDTO.getBirthday());
            employee.setGender(employeeDTO.getGender());
            employee.setAddress(employeeDTO.getAddress());
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
            employee.setPositionCode(employeePosition);
            employee.setStatus(employeeDTO.getStatus());
            employee.setPassword(emp.getPassword());
            employee.setEmail(employeeDTO.getEmail());           
            employee.setClassCode(classanimal);
            employee.setActivated(employeeDTO.getActivated());

            employee.setPhoto(emp.getPhoto());
            
            repository.save(employee);
            
            return true;
            
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
        
        }      
    }

    @Override
    public Employee checkLogin(String code, String password) {
        try {
            String passwordEncrypt= EncryptDecrypt.Encrypt(password);
            return repository.checkLogin(code, passwordEncrypt);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Employee> searchCode(String code) {
         try {
            List<Employee> list = repository.searchByCode("%"+code+"%");
            return list;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Employee> searchName(String name) {
        try {
            List<Employee> list = repository.searchByName("%"+name+"%");
            return list;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(String code) {
         try {
            repository.deleteById(code);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean resetPassword(String code) {
        try {
            Employee employee = repository.findByCode(code);
            
            String passwordEncrypt= EncryptDecrypt.Encrypt("123");
            employee.setPassword(passwordEncrypt);
            
            repository.save(employee);          
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean updatePass(String code,String password) {
        try {
            Employee emp = repository.findByCode(code);
            String passwordEncrypt= EncryptDecrypt.Encrypt(password);
            emp.setPassword(passwordEncrypt);
            
            repository.save(emp);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
    @Override
    public boolean codeExist(String employeeCode) {
        return repository.findById(employeeCode).isPresent();
    }

    @Override
    public List<Employee> findAllExcept() {
        return repository.findAllExcept();
    }

    @Override
    public boolean updateAPI(Employee eapi) {
        try {
            Employee emp = repository.findByCode(eapi.getEmployeeCode());
            
            Employeeposition employeePosition= new Employeeposition();
            employeePosition.setPositionCode(eapi.getPositionCode().getPositionCode());
            
            Classanimal classanimal = new Classanimal();
            classanimal.setClassCode(eapi.getClassCode().getClassCode());                        
            
            Employee employee = new Employee();
            employee.setEmployeeCode(eapi.getEmployeeCode());
            employee.setEmployeeName(eapi.getEmployeeName());
            employee.setBirthday(eapi.getBirthday());
            employee.setGender(eapi.getGender());
            employee.setAddress(eapi.getAddress());
            employee.setPhoneNumber(eapi.getPhoneNumber());
            employee.setPositionCode(employeePosition);
            employee.setStatus(eapi.getStatus());
            employee.setPassword(emp.getPassword());
            employee.setEmail(eapi.getEmail());           
            employee.setClassCode(classanimal);
            employee.setActivated(eapi.getActivated());

            employee.setPhoto(emp.getPhoto());
            
            repository.save(employee);
            
            return true;
            
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    
    }
}
