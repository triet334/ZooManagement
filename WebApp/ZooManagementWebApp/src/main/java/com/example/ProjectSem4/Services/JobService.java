/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Jobsforemployee;
import com.example.ProjectSem4.IServices.IJob;
import com.example.ProjectSem4.Repositories.JobRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class JobService implements IJob{
    
    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Jobsforemployee> getJobList() {
        try {
            return jobRepository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void saveJob(Jobsforemployee job) {
         try {                    
            jobRepository.save(job);                            
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteJob(Jobsforemployee job) {
         try {
            jobRepository.delete(job);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public Jobsforemployee findOne(int id) {
         try {
            return jobRepository.findById(id);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Jobsforemployee> findAllSort(String code) {
        try {
          return jobRepository.findAllSort(code);
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

//    @Override
//    public List<Jobsforemployee> findByEmployee(String code) {
//        try {
//          return jobRepository.findAllSort(code);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//        return null;
//    }

    @Override
    public List<Jobsforemployee> findByEmpCode(String employeeCode, int shiftCode) {
        try {
            return jobRepository.findByEmpCode(employeeCode, shiftCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

//    @Override
//    public boolean dateExist(String workdate) {
//        return jobRepository.searchDate(workdate).isEmpty();
//    }

    @Override
    public List<Jobsforemployee> findByDT(String date, int Scode) {
        try {
          return jobRepository.findByDT(date,Scode);
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public List<Jobsforemployee> findByDate(String date) {
         try {
          return jobRepository.findByDate(date);
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public List<Jobsforemployee> findByShift(int shiftCode) {
         try {
          return jobRepository.findByShift(shiftCode);
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }
    
}
