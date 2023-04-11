/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.DTO;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class EmployeeDTO implements Serializable {

    //@NotNull
    @Size(min = 1, max = 50)
    @NotBlank(message = "Employee Code can not blank !")
    private String employeeCode;
    //@NotNull
    @NotBlank(message = "Employee Name can not blank !")
    private String employeeName;
    @NotBlank(message = "Birthday can not blank !")
    private String birthday;
    private boolean gender;
    @NotBlank(message = "Address can not blank !")
    private String address;
    @NotBlank(message = "Phone Number can not blank !")
    private String phoneNumber;
    private Integer positionCode;
    private Boolean status;
    @Email
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    private String email;
    //@NotNull
    @NotBlank(message = "Password can not blank !")
    private String password;
    private MultipartFile photo;    
    private Integer classCode;
    private Integer shiftCode;
    private boolean activated;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public EmployeeDTO(String employeeCode, String employeeName, String birthday, boolean gender, String address, String phoneNumber, Integer positionCode, Boolean status, String email, String password, MultipartFile photo, Integer classCode, Integer shiftCode, boolean activated) {
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.positionCode = positionCode;
        this.status = status;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.classCode = classCode;
        this.shiftCode = shiftCode;
        this.activated = activated;
    }

    public EmployeeDTO() {
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

    public Integer getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(Integer shiftCode) {
        this.shiftCode = shiftCode;
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

    public Integer getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Integer positionCode) {
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

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
