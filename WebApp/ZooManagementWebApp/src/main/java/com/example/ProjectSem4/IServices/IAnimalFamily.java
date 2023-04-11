/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.Entities.*;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IAnimalFamily {
    public List<Animalfamily> getAnimalFamilyList();
    
    public Animalfamily getOneAnimalfamily(int familyCode);
    
    public List<Animalfamily> findAnimalFamilyByClass(int familyCode);
}
