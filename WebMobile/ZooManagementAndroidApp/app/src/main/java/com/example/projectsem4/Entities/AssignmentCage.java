package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignmentCage implements Serializable {

    @SerializedName("assignmentCode")
    private int assignmentCode;

    @SerializedName("cageCode")
    private Cage cageCode;

    @SerializedName("employeeCode")
    private Employee employeeCode;

    @Override
    public String toString() {
        return cageCode.getCageName();
    }

    public AssignmentCage(int assignmentCode, Cage cageCode, Employee employeeCode) {
        this.assignmentCode = assignmentCode;
        this.cageCode = cageCode;
        this.employeeCode = employeeCode;
    }

    public int getAssignmentCode() {
        return assignmentCode;
    }

    public void setAssignmentCode(int assignmentCode) {
        this.assignmentCode = assignmentCode;
    }

    public Cage getCageCode() {
        return cageCode;
    }

    public void setCageCode(Cage cageCode) {
        this.cageCode = cageCode;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public AssignmentCage() {
    }
}
