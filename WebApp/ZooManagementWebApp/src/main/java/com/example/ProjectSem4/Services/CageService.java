/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Cage;
import com.example.ProjectSem4.IServices.ICage;
import com.example.ProjectSem4.Repositories.CageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CageService implements ICage{
    @Autowired
    CageRepository cageRepository;

    @Override
    public List<Cage> getCageList() {
        try {
            return cageRepository.findAll();            
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public List<Cage> findCagesByAnimalFamily(int code) {
        try {
            return cageRepository.findCagesByAnimalFamily(code);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public Cage findCageByCode(int code) {
        try {
            return cageRepository.findCageByCode(code);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
            
}
