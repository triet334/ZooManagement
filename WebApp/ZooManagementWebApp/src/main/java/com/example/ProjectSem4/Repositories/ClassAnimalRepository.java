/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.Classanimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Admin
 */
public interface ClassAnimalRepository extends JpaRepository<Classanimal, Integer> {
//    @Query("Select new com.example.ProjectSem4.Entities.Classanimal(classCode, className) from Classanimal Where employeeCode.employeeCode = :code")
//    public List<Cage> findClassAnimalByEmployee(@Param("code") int code);
    
//    @Query("SELECT c FROM Classanimal c WHERE c.classCode = :code")
//    public Classanimal findByCode(@Param("code") int code);
    
}
