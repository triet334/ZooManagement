package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cage implements Serializable {

    @SerializedName("cageCode")
    private int cageCode;

    @SerializedName("cageName")
    private String cageName;

    @SerializedName("familyCode")
    private AnimalFamily familyCode;

    @SerializedName("maxEmployee")
    private int maxEmployee;

    @SerializedName("maxAnimal")
    private int maxAnimal;

    public int getCageCode() {
        return cageCode;
    }

    public void setCageCode(int cageCode) {
        this.cageCode = cageCode;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;
    }

    public AnimalFamily getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(AnimalFamily familyCode) {
        this.familyCode = familyCode;
    }

    public int getMaxEmployee() {
        return maxEmployee;
    }

    public void setMaxEmployee(int maxEmployee) {
        this.maxEmployee = maxEmployee;
    }

    public int getMaxAnimal() {
        return maxAnimal;
    }

    public void setMaxAnimal(int maxAnimal) {
        this.maxAnimal = maxAnimal;
    }

    public Cage(int cageCode, String cageName, AnimalFamily familyCode, int maxEmployee, int maxAnimal) {
        this.cageCode = cageCode;
        this.cageName = cageName;
        this.familyCode = familyCode;
        this.maxEmployee = maxEmployee;
        this.maxAnimal = maxAnimal;
    }

    public Cage() {
    }
}
