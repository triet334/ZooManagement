package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmployeePosition implements Serializable {

    @SerializedName("positionCode")
    private int positionCode;

    @SerializedName("positionName")
    private String positionName;

    public EmployeePosition() {
    }

    public EmployeePosition(int positionCode, String positionName) {
        this.positionCode = positionCode;
        this.positionName = positionName;
    }

    public int getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(int positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
