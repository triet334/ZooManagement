package com.example.projectsem4.Entities;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employee implements Serializable {

    @SerializedName("employeeCode")
    private String employeeCode;
    @SerializedName("employeeName")
    private String employeeName;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("gender")
    private Boolean gender;
    @SerializedName("address")
    private String address;
    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("positionCode")
    private EmployeePosition positionCode;
    @SerializedName("classCode")
    private ClassAnimal classCode;

    @SerializedName("status")
    private Boolean status;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("photo")
    private String photo;

    public Employee() {
    }

    public Employee(String employeeCode, String employeeName, String birthday, Boolean gender, String address, String phoneNumber, EmployeePosition positionCode, ClassAnimal classCode, Boolean status, String email, String password, String photo) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.positionCode = positionCode;
        this.classCode = classCode;
        this.status = status;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public ClassAnimal getClassCode() {
        return classCode;
    }

    public void setClassCode(ClassAnimal classCode) {
        this.classCode = classCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeePosition getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(EmployeePosition positionCode) {
        this.positionCode = positionCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
