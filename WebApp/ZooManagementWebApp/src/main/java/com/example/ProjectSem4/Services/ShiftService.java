/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.Entities.ShiftChange;
import com.example.ProjectSem4.IServices.IShift;
import com.example.ProjectSem4.Repositories.ShiftRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ShiftService implements IShift{

     @Autowired
    ShiftRepository shiftRepository;
    
    @Override
    public List<ShiftChange> getShiftList() {
        try {
            return shiftRepository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
}
