/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmployeeCode", query = "SELECT e FROM Employee e WHERE e.employeeCode = :employeeCode"),
    @NamedQuery(name = "Employee.findByEmployeeName", query = "SELECT e FROM Employee e WHERE e.employeeName = :employeeName"),
    @NamedQuery(name = "Employee.findByBirthday", query = "SELECT e FROM Employee e WHERE e.birthday = :birthday"),
    @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender"),
    @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address"),
    @NamedQuery(name = "Employee.findByPhoneNumber", query = "SELECT e FROM Employee e WHERE e.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Employee.findByStatus", query = "SELECT e FROM Employee e WHERE e.status = :status"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findByPassword", query = "SELECT e FROM Employee e WHERE e.password = :password"),
    @NamedQuery(name = "Employee.findByPhoto", query = "SELECT e FROM Employee e WHERE e.photo = :photo"),
    @NamedQuery(name = "Employee.findByActivated", query = "SELECT e FROM Employee e WHERE e.activated = :activated")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "employeeCode")
    @NotBlank(message = "Employee Code can not blank !")
    private String employeeCode;
    @Size(max = 150)
    @Column(name = "employeeName")
    @NotBlank(message = "Employee Name can not blank !")
    private String employeeName;
    @Size(max = 150)
    @Column(name = "birthday")
    @NotBlank(message = "Birthday can not blank !")
    private String birthday;
    @Column(name = "gender")
    private Boolean gender;
    @Size(max = 250)
    @Column(name = "address")
    @NotBlank(message = "Address can not blank !")
    private String address;
    @Size(max = 50)
    @Column(name = "phoneNumber")
    @NotBlank(message = "Phone Number can not blank !")
    private String phoneNumber;
    @Column(name = "status")
    private Boolean status;
    @Email
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 350)
    @Column(name = "email")
    @NotBlank(message = "Email can not blank !")
     
    private String email;
    @Size(max = 250)
    @Column(name = "password")
    @NotBlank(message = "Password can not blank !")
    private String password;
    @Size(max = 2147483647)
    @Column(name = "photo")
    private String photo;
    @Column(name = "activated")
    private Boolean activated;
    @JoinColumn(name = "classCode", referencedColumnName = "classCode")
    @ManyToOne
    //@JsonIgnore
    private Classanimal classCode;
    @JoinColumn(name = "positionCode", referencedColumnName = "positionCode")
    @ManyToOne
    //@JsonIgnore
    private Employeeposition positionCode;
    @OneToMany(mappedBy = "employeeCode")
    @JsonIgnore
    private List<Jobsforemployee> jobsforemployeeList;
    @OneToMany(mappedBy = "employeeCode")
    @JsonIgnore
    private List<Healthtracking> healthtrackingList;
    @OneToMany(mappedBy = "doctorCode")
    @JsonIgnore
    private List<Healthtracking> healthtrackingList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeCode")
    @JsonIgnore
    private List<Assignmentforemployeeandcage> assignmentforemployeeandcageList;

    public Employee() {
    }

    public Employee(String employeeCode) {
        this.employeeCode = employeeCode;
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

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Classanimal getClassCode() {
        return classCode;
    }

    public void setClassCode(Classanimal classCode) {
        this.classCode = classCode;
    }

    public Employeeposition getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Employeeposition positionCode) {
        this.positionCode = positionCode;
    }

    @XmlTransient
    public List<Jobsforemployee> getJobsforemployeeList() {
        return jobsforemployeeList;
    }

    public void setJobsforemployeeList(List<Jobsforemployee> jobsforemployeeList) {
        this.jobsforemployeeList = jobsforemployeeList;
    }

    @XmlTransient
    public List<Healthtracking> getHealthtrackingList() {
        return healthtrackingList;
    }

    public void setHealthtrackingList(List<Healthtracking> healthtrackingList) {
        this.healthtrackingList = healthtrackingList;
    }

    @XmlTransient
    public List<Healthtracking> getHealthtrackingList1() {
        return healthtrackingList1;
    }

    public void setHealthtrackingList1(List<Healthtracking> healthtrackingList1) {
        this.healthtrackingList1 = healthtrackingList1;
    }

    @XmlTransient
    public List<Assignmentforemployeeandcage> getAssignmentforemployeeandcageList() {
        return assignmentforemployeeandcageList;
    }

    public void setAssignmentforemployeeandcageList(List<Assignmentforemployeeandcage> assignmentforemployeeandcageList) {
        this.assignmentforemployeeandcageList = assignmentforemployeeandcageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeCode != null ? employeeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeCode == null && other.employeeCode != null) || (this.employeeCode != null && !this.employeeCode.equals(other.employeeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Employee[ employeeCode=" + employeeCode + " ]";
    }
    
}
