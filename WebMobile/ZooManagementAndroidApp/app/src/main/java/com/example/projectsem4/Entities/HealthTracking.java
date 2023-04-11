package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HealthTracking implements Serializable {

    @SerializedName("healthTrackingId")
    private Integer healthTrackingId;

    @SerializedName("animalCode")
    private Animal animalCode;

//    @SerializedName("cageCode")
//    private Cage cageCode;

    @SerializedName("healthStatus")
    private Boolean healthStatus;

    @SerializedName("employeeDescription")
    private String employeeDescription;

    @SerializedName("doctorNote")
    private String doctorNote;

    @SerializedName("doctorCode")
    private Employee doctorCode;

    @SerializedName("employeeCode")
    private Employee employeeCode;

    public Integer getHealthTrackingId() {
        return healthTrackingId;
    }

    public void setHealthTrackingId(Integer healthTrackingId) {
        this.healthTrackingId = healthTrackingId;
    }

    public Animal getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(Animal animalCode) {
        this.animalCode = animalCode;
    }

//    public Cage getCageCode() {
//        return cageCode;
//    }

//    public void setCageCode(Cage cageCode) {
//        this.cageCode = cageCode;
//    }

    public Boolean getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(Boolean healthStatus) {
        this.healthStatus = healthStatus;
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

    public HealthTracking(Integer healthTrackingId, Animal animalCode, Boolean healthStatus, String employeeDescription, String doctorNote, Employee doctorCode, Employee employeeCode) {
        this.healthTrackingId = healthTrackingId;
        this.animalCode = animalCode;
        //this.cageCode = cageCode;
        this.healthStatus = healthStatus;
        this.employeeDescription = employeeDescription;
        this.doctorNote = doctorNote;
        this.doctorCode = doctorCode;
        this.employeeCode = employeeCode;
    }

    public HealthTracking() {

    }

}
