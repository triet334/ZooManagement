/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.Entities.Healthtracking;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IHealthTracking {
 
    public List<Healthtracking> findAll();
    
    public List<Healthtracking> findStatus();
    
    public boolean createHealth(Healthtracking healthTracking);
    
    public boolean updateHealth(Healthtracking healthTracking);
    
    public boolean deleteHealth(int id);
    
    public Healthtracking findOne(int id);
}
