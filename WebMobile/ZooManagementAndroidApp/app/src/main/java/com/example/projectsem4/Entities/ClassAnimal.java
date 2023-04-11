package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClassAnimal implements Serializable {

    @SerializedName("classCode")
    private int classCode;

    @SerializedName("className")
    private String className;

    public ClassAnimal() {
    }

    public ClassAnimal(int classCode, String className) {
        this.classCode = classCode;
        this.className = className;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
