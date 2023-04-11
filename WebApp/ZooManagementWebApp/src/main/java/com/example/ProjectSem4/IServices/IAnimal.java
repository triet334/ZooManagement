/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.IServices;

import com.example.ProjectSem4.DTO.AnimalDTO;
import com.example.ProjectSem4.Entities.Animal;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IAnimal {

    public List<Animal> getAnimalList();

    public boolean createAnimal(AnimalDTO newAnimal);

    public boolean deleteAnimal(String animcalCode);

    public Animal getOne(String animalCode);

    public List<Animal> findbyCage(int code);

    public int countAnimalInCage(int code);

    public boolean codeExist(String animalCode);
}
