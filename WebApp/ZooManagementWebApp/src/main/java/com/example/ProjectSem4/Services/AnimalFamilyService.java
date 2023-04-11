/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.IAnimalFamily;
import com.example.ProjectSem4.Repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AnimalFamilyService implements IAnimalFamily{
    @Autowired
    AnimalFamilyRepository animalFamilyRepository;        

    @Override
    public List<Animalfamily> getAnimalFamilyList() {
        try {
            return animalFamilyRepository.findAll();            
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public Animalfamily getOneAnimalfamily(int familyCode) {
        try {
            return animalFamilyRepository.findOne(familyCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Animalfamily> findAnimalFamilyByClass(int familyCode) {
        try {
            return animalFamilyRepository.findAnimalFamilyByClass(familyCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
