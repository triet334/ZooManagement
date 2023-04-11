/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Healthtracking;
import com.example.ProjectSem4.IServices.IHealthTracking;
import com.example.ProjectSem4.Repositories.HealthTrackingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HealthTrackingService implements IHealthTracking{
     @Autowired
    HealthTrackingRepository repository;
     
    @Override
    public List<Healthtracking> findAll() {
        try {
          return repository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean createHealth(Healthtracking healthTracking) {
        try {
            healthTracking.setHealthStatus(false);
            repository.save(healthTracking);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean updateHealth(Healthtracking healthTracking) {
         try {
            repository.save(healthTracking);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
         return false;
    }

    @Override
    public boolean deleteHealth(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public Healthtracking findOne(int id) {
         try {
            return repository.findOne(id);            
        } catch (Exception e) {
            e.getMessage();
        }
          return null;
    }

    @Override
    public List<Healthtracking> findStatus() {
        try {
          return repository.findStatus();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
}
