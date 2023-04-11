/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Cage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Admin
 */
public interface CageRepository extends JpaRepository<Cage, Integer> {
    @Query("Select new com.example.ProjectSem4.Entities.Cage(cageCode, cageName) from Cage Where familyCode.familyCode = :code")
    public List<Cage> findCagesByAnimalFamily(@Param("code") int code);
    
    @Query("SELECT c FROM Cage c WHERE c.cageCode = :code")
    public Cage findCageByCode(@Param("code") int code);
    
}
