/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Reportdetail;
import com.example.ProjectSem4.IServices.IReportDetail;
import com.example.ProjectSem4.Repositories.ReportDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruava
 */
@Service
public class ReportDetailService implements IReportDetail{
    @Autowired
    ReportDetailRepository repository;
    
    @Override
    public List<Reportdetail> findByReport(int reportid) {
        try {
          return repository.findByReport(reportid);
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public boolean create(Reportdetail reportdetail) {
        try {
            repository.save(reportdetail);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
       return false;
    }
    
}
