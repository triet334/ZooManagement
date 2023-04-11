/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface ReportRepository extends JpaRepository<Report, Integer> {   
    @Query("SELECT r FROM Report r ORDER BY r.reportId DESC")
    List<Report> findAllSort();
    
    @Query("SELECT r FROM Report r WHERE r.reportId = :reportId")
    Report findOne(@PathVariable("reportId") int reportId);
            
    @Query("SELECT r FROM Report r WHERE r.date = :date")
    List<Report> findByDate(@PathVariable("date") String date);
    
    @Query(value = "SELECT * FROM Report WHERE cageCode = :cageCode ORDER BY reportId DESC",nativeQuery = true)
    List<Report> findByCage(@PathVariable("cageCode") int cageCode);
    
    @Query(value = "SELECT * FROM Report WHERE date = :date AND (checkCage = 0 OR feeding = 0 OR clean = 0)",nativeQuery = true)
    List<Report> findF(@PathVariable("date") String date);
   
}
