/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.Entities.Healthdetail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ruava
 */
public interface IHealthDetail {
    @Autowired
    List<Healthdetail> findByHealth(int healthid);
    
    @Autowired
    boolean create(Healthdetail healthdetail);
}
