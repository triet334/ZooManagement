package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Animal implements Serializable {

    @SerializedName("animalCode")
    private String animalCode;

    @SerializedName("animalName")
    private String animalName;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("familyCode")
    private AnimalFamily familyCode;

    @SerializedName("classCode")
    private ClassAnimal classCode;

    @SerializedName("cageCode")
    private Cage cageCode;

    @SerializedName("dateAdded")
    private String dateAdded;

    @SerializedName("originCountry")
    private String originCountry;

    @SerializedName("gender")
    private Boolean gender;

    @SerializedName("healthStatus")
    private String healthStatus;

    @SerializedName("photo")
    private String photo;

    @Override
    public String toString() {
        return animalName;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public AnimalFamily getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(AnimalFamily familyCode) {
        this.familyCode = familyCode;
    }

    public ClassAnimal getClassCode() {
        return classCode;
    }

    public void setClassCode(ClassAnimal classCode) {
        this.classCode = classCode;
    }

    public Cage getCageCode() {
        return cageCode;
    }

    public void setCageCode(Cage cageCode) {
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Animal(String animalCode, String animalName, String birthday, AnimalFamily familyCode, ClassAnimal classCode, Cage cageCode, String dateAdded, String originCountry, Boolean gender, String healthStatus, String photo) {
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

    public Animal() {
    }
}
