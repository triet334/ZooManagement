/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Report;
import com.example.ProjectSem4.IServices.IReport;
import com.example.ProjectSem4.Repositories.ReportRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReportService implements IReport{
     @Autowired
    ReportRepository repository;
     
    @Override
    public List<Report> findAll() {
         try {
          return repository.findAllSort();
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public boolean createReport(Report report) {
       try {
            repository.save(report);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
       return false;
    }

    @Override
    public boolean updateReport(Report report) {
          try {
            repository.save(report);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
          return false;
    }

    @Override
    public boolean deleteReport(int id) {
       try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
       return false;
    }

    @Override
    public Report findOne(int id) {
         try {
            return repository.findOne(id);            
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public List<Report> findByDate(String date) {
        try {
          return repository.findByDate(date);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Report> findByCage(int code) {
        try {
          return repository.findByCage(code);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Report> findF(String date) {
        try {
          return repository.findF(date);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
}
