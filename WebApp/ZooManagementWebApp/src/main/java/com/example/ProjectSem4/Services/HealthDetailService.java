/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.Healthdetail;
import com.example.ProjectSem4.IServices.IHealthDetail;
import com.example.ProjectSem4.Repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruava
 */
@Service
public class HealthDetailService implements IHealthDetail{   
    @Autowired
    HealthDetailRepository repository;
    
    @Override
    public List<Healthdetail> findByHealth(int healthid) {
        try {
          return repository.findByHealth(healthid);
        } catch (Exception e) {
            e.getMessage();
        }
         return null;
    }

    @Override
    public boolean create(Healthdetail healthdetail) {
        try {
            repository.save(healthdetail);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
       return false;
    }
    
}
