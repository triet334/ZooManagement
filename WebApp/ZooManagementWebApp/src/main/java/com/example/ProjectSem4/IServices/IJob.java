/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.Entities.Jobsforemployee;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IJob {

    public List<Jobsforemployee> getJobList();

    public List<Jobsforemployee> findAllSort(String code);

    public void saveJob(Jobsforemployee job);

    public void deleteJob(Jobsforemployee job);

    public Jobsforemployee findOne(int id);

//    public  List<Jobsforemployee> findByEmployee(String code);
    public List<Jobsforemployee> findByEmpCode(String employeeCode, int shiftCode);

    public List<Jobsforemployee> findByDate(String date);

    public List<Jobsforemployee> findByDT(String date, int Scode);

    public List<Jobsforemployee> findByShift(int shiftCode);

}
