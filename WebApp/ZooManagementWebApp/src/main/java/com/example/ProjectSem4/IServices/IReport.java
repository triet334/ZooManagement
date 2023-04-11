/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.Entities.Report;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Admin
 */
public interface IReport {                          
           
    @Autowired
    List<Report> findAll();
    
    @Autowired
    List<Report> findByDate(String date);
    
    @Autowired
    boolean createReport(Report report);
    
    @Autowired
    boolean updateReport(Report report);
    
    @Autowired
    boolean deleteReport(int id);
    
    @Autowired
    Report findOne(int id);
    
    @Autowired
    List<Report> findByCage(int code);
    
    @Autowired
    List<Report> findF(String date);
}
