/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Repositories;

import com.example.ProjectSem4.Entities.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface AnimalRepository extends JpaRepository<Animal, String> {

    @Query("SELECT a FROM Animal a WHERE a.animalCode = :animalCode")
    public Animal findOne(@PathVariable("animalCode") String animalCode);

    @Query("SELECT new com.example.ProjectSem4.Entities.Animal(animalCode, animalName) FROM Animal WHERE cageCode.cageCode = :code")
    public List<Animal> findbyCage(@Param("code") int code);

    @Query(value = "Select Count(cageCode) from Animal where cageCode = :code", nativeQuery = true)
    public int countAnimalInCage(@PathVariable("code") int code);

}
