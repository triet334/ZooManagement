/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Services;

import com.example.ProjectSem4.DTO.AnimalDTO;
import com.example.ProjectSem4.Entities.*;
import com.example.ProjectSem4.IServices.*;
import com.example.ProjectSem4.Repositories.AnimalRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service
public class AnimalService implements IAnimal {

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public List<Animal> getAnimalList() {
        try {
            return animalRepository.findAll();
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean createAnimal(AnimalDTO newAnimal) {
        if (!newAnimal.getPhoto().isEmpty()) {
            try {
                MultipartFile file = newAnimal.getPhoto();
                byte[] fi = file.getBytes();

                String base64 = "";
                base64 = Base64.getEncoder().encodeToString(fi);

                Animal animal = new Animal();
                animal.setAnimalCode(newAnimal.getAnimalCode());
                animal.setAnimalName(newAnimal.getAnimalName());
                animal.setBirthday(newAnimal.getBirthday());

                Animalfamily animalfamily = new Animalfamily();
                animalfamily.setFamilyCode(newAnimal.getFamilyCode());
                animal.setFamilyCode(animalfamily);
                
                Classanimal classAnimal = new Classanimal();
                classAnimal.setClassCode(newAnimal.getClassCode());
                animal.setClassCode(classAnimal);

                Cage cage = new Cage();
                cage.setCageCode(newAnimal.getCageCode());
                animal.setCageCode(cage);                                

                animal.setDateAdded(newAnimal.getDateAdded());
                animal.setOriginCountry(newAnimal.getOriginCountry());
                animal.setGender(newAnimal.getGender());
                animal.setHealthStatus(newAnimal.getHealthStatus());
                animal.setPhoto(base64);

                animalRepository.save(animal);

                return true;
            } catch (IOException e) {
                e.getMessage();
            }
            return false;
        } else {
            try {
                Animal oneAnimal = getOne(newAnimal.getAnimalCode());
                String ph = oneAnimal.getPhoto();

                Animal animal = new Animal();
                animal.setAnimalCode(newAnimal.getAnimalCode());
                animal.setAnimalName(newAnimal.getAnimalName());
                animal.setBirthday(newAnimal.getBirthday());

                Animalfamily animalfamily = new Animalfamily();
                animalfamily.setFamilyCode(newAnimal.getFamilyCode());
                animal.setFamilyCode(animalfamily);

                Cage cage = new Cage();
                cage.setCageCode(newAnimal.getCageCode());
                animal.setCageCode(cage);
                
                Classanimal classAnimal = new Classanimal();
                classAnimal.setClassCode(newAnimal.getClassCode());
                animal.setClassCode(classAnimal);

                animal.setDateAdded(newAnimal.getDateAdded());
                animal.setOriginCountry(newAnimal.getOriginCountry());
                animal.setGender(newAnimal.getGender());
                animal.setHealthStatus(newAnimal.getHealthStatus());
                animal.setPhoto(ph);

                animalRepository.save(animal);

                return true;
            } catch (Exception e) {
                e.getMessage();
            }
            return false;
        }

    }

    @Override
    public Animal getOne(String animalCode) {
        try {
            return animalRepository.findOne(animalCode);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    public boolean deleteAnimal(String animcalCode) {
        try {
            animalRepository.deleteById(animcalCode);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public List<Animal> findbyCage(int code) {
        try {
            return animalRepository.findbyCage(code);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

	@Override
    public int countAnimalInCage(int code) {
        try {
            int count = animalRepository.countAnimalInCage(code);
            return count;
        } catch (Exception e) {
            e.getMessage();
        }
        return 3;
    } 
	
    @Override
    public boolean codeExist(String animalCode) {
        return animalRepository.findById(animalCode).isPresent();
    }
}
