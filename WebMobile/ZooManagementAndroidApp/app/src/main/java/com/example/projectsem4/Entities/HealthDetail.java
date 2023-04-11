package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HealthDetail implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("healthid")
    private HealthTracking healthid;

    @SerializedName("employeeDescription")
    private String employeeDescription;

    @SerializedName("doctorNote")
    private String doctorNote;

    @SerializedName("doctorCode")
    private Employee doctorCode;

    @SerializedName("employeeCode")
    private Employee employeeCode;

    @SerializedName("time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HealthTracking getHealthid() {
        return healthid;
    }

    public void setHealthid(HealthTracking healthid) {
        this.healthid = healthid;
    }

    public String getEmployeeDescription() {
        return employeeDescription;
    }

    public void setEmployeeDescription(String employeeDescription) {
        this.employeeDescription = employeeDescription;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public Employee getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(Employee doctorCode) {
        this.doctorCode = doctorCode;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public HealthDetail(int id, HealthTracking healthid, String employeeDescription, String doctorNote, Employee doctorCode, Employee employeeCode, String time) {
        this.id = id;
        this.healthid = healthid;
        this.employeeDescription = employeeDescription;
        this.doctorNote = doctorNote;
        this.doctorCode = doctorCode;
        this.employeeCode = employeeCode;
        this.time = time;
    }

    public HealthDetail() {
    }
}
