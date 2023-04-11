/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Healthtracking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface HealthTrackingRepository extends JpaRepository<Healthtracking, Integer> {
    @Query("SELECT h FROM Healthtracking h WHERE h.healthTrackingId = :healthTrackingId")            
    Healthtracking findOne(@PathVariable("healthTrackingId") int healthTrackingId);
    
    @Query("SELECT h FROM Healthtracking h WHERE h.healthStatus = False")            
    List<Healthtracking> findStatus();
}
