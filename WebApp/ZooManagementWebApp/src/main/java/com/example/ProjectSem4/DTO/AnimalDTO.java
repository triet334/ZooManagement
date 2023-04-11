/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.DTO;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class AnimalDTO implements Serializable{
    
    @NotNull
    @NotBlank(message = "Animal Code can not blank !")
    @Size(min = 1, max = 50,message = "Animal Name size must be between 1 and 50 !")
    private String animalCode;
    @NotBlank(message = "Animal Name can not blank !")
    @Size(min = 2, max = 150, message = "Animal Name size must be between 2 and 150 !")
    private String animalName;
    @NotBlank(message = "Year of Birth can not blank !")
    private String birthday;
    private int familyCode;
    private int classCode;
    private int cageCode;
    @NotBlank(message = "Received Date can not blank !")
    private String dateAdded;
    @NotBlank(message = "Nationality can not blank !")
    private String originCountry;
    private boolean gender;
    @NotBlank(message = "Health Status can not blank !")
    private String healthStatus;
    private MultipartFile photo;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public AnimalDTO() {
    }

    public AnimalDTO(String animalCode, String animalName, String birthday, int familyCode, int classCode, int cageCode, String dateAdded, String originCountry, boolean gender, String healthStatus, MultipartFile photo) {
        this.animalCode = animalCode;
        this.animalName = animalName;
        this.birthday = birthday;
        this.familyCode = familyCode;
        this.classCode = classCode;
        this.cageCode = cageCode;
        this.dateAdded = dateAdded;
        this.originCountry = originCountry;
        this.gender = gender;
        this.healthStatus = healthStatus;
        this.photo = photo;
    }  

    public String getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(String animalCode) {
        this.animalCode = animalCode;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }
    
    public int getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(int familyCode) {
        this.familyCode = familyCode;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public int getCageCode() {
        return cageCode;
    }

    public void setCageCode(int cageCode) {
        this.cageCode = cageCode;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }

    
    
}
