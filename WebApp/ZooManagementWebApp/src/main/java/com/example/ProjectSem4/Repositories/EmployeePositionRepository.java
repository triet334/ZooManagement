/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Employeeposition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Admin
 */
public interface EmployeePositionRepository extends JpaRepository<Employeeposition, Integer> {
    @Query("SELECT e FROM Employeeposition e WHERE e.positionCode != 1")
    List<Employeeposition> find();
}
