package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnimalFamily implements Serializable {

    @SerializedName("familyCode")
    private int familyCode;

    @SerializedName("familyName")
    private String familyName;

    @SerializedName("classCode")
    private ClassAnimal classCode;

    public int getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(int familyCode) {
        this.familyCode = familyCode;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public ClassAnimal getClassCode() {
        return classCode;
    }

    public void setClassCode(ClassAnimal classCode) {
        this.classCode = classCode;
    }

    public AnimalFamily() {
    }

    public AnimalFamily(int familyCode, String familyName, ClassAnimal classCode) {
        this.familyCode = familyCode;
        this.familyName = familyName;
        this.classCode = classCode;
    }
}
