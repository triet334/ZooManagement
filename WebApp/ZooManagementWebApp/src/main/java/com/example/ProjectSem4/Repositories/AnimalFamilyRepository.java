/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;
import com.example.ProjectSem4.Entities.Animalfamily;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface AnimalFamilyRepository extends JpaRepository<Animalfamily, Integer> {
    @Query("SELECT a FROM Animalfamily a WHERE a.familyCode = :familyCode")
    Animalfamily findOne(@PathVariable("familyCode") int familyCode);
    
    @Query("Select new com.example.ProjectSem4.Entities.Animalfamily(familyCode, familyName) from Animalfamily Where classCode.classCode = :code")
    public List<Animalfamily> findAnimalFamilyByClass(@PathVariable("code") int code);
}
