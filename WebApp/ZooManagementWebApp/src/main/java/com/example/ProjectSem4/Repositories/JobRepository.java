/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Jobsforemployee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface JobRepository extends JpaRepository<Jobsforemployee, Integer> {
    
    public Jobsforemployee findById(int id);
    
    @Query(value="SELECT * FROM Jobsforemployee  WHERE employeeCode = :employeeCode ORDER BY feedTableId DESC",nativeQuery = true)            
    List<Jobsforemployee> findAllSort(@PathVariable("employeeCode") String employeeCode);

    @Query(value = "SELECT * FROM Jobsforemployee  WHERE employeeCode = :employeeCode AND shiftCode =:shiftCode", nativeQuery = true)
    List<Jobsforemployee> findByEmpCode(@PathVariable("employeeCode") String employeeCode, @PathVariable("shiftCode") int shiftCode);
    
    @Query(value = "SELECT * FROM Jobsforemployee  WHERE shiftCode =:shiftCode", nativeQuery = true)
    List<Jobsforemployee> findByShift(@PathVariable("shiftCode") int shiftCode);
    
    @Query("SELECT j FROM Jobsforemployee j WHERE j.workDate = :workDate")
    List<Jobsforemployee> findByDate(@PathVariable("workDate") String workDate);
	
	//test
    @Query(value="SELECT * FROM Jobsforemployee  WHERE workDate = :workDate AND shiftCode=:shiftCode",nativeQuery = true)            
    List<Jobsforemployee> findByDT(@PathVariable("workDate") String workDate,@PathVariable("shiftCode") int shiftCode);
    
}
