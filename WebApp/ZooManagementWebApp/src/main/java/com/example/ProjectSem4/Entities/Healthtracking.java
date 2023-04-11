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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "healthtracking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Healthtracking.findAll", query = "SELECT h FROM Healthtracking h"),
    @NamedQuery(name = "Healthtracking.findByHealthTrackingId", query = "SELECT h FROM Healthtracking h WHERE h.healthTrackingId = :healthTrackingId"),
    @NamedQuery(name = "Healthtracking.findByHealthStatus", query = "SELECT h FROM Healthtracking h WHERE h.healthStatus = :healthStatus"),
    @NamedQuery(name = "Healthtracking.findByEmployeeDescription", query = "SELECT h FROM Healthtracking h WHERE h.employeeDescription = :employeeDescription"),
    @NamedQuery(name = "Healthtracking.findByDoctorNote", query = "SELECT h FROM Healthtracking h WHERE h.doctorNote = :doctorNote")})
public class Healthtracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "healthTrackingId")
    private Integer healthTrackingId;
    @Column(name = "healthStatus")
    private Boolean healthStatus;
    @Size(max = 350)
    @Column(name = "employeeDescription")
    private String employeeDescription;
    @Size(max = 350)
    @Column(name = "doctorNote")
    private String doctorNote;
    @JoinColumn(name = "animalCode", referencedColumnName = "animalCode")
    @ManyToOne
    //@JsonIgnore
    private Animal animalCode;
    @JoinColumn(name = "employeeCode", referencedColumnName = "employeeCode")
    @ManyToOne
    //@JsonIgnore
    private Employee employeeCode;
    @JoinColumn(name = "doctorCode", referencedColumnName = "employeeCode")
    @ManyToOne
    //@JsonIgnore
    private Employee doctorCode;
    @OneToMany(mappedBy = "healthid")
    @JsonIgnore
    private List<Healthdetail> healthdetailList;

    public Healthtracking() {
    }

    public Healthtracking(Integer healthTrackingId) {
        this.healthTrackingId = healthTrackingId;
    }

    public Integer getHealthTrackingId() {
        return healthTrackingId;
    }

    public void setHealthTrackingId(Integer healthTrackingId) {
        this.healthTrackingId = healthTrackingId;
    }

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

    public Animal getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(Animal animalCode) {
        this.animalCode = animalCode;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Employee getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(Employee doctorCode) {
        this.doctorCode = doctorCode;
    }

    @XmlTransient
    public List<Healthdetail> getHealthdetailList() {
        return healthdetailList;
    }

    public void setHealthdetailList(List<Healthdetail> healthdetailList) {
        this.healthdetailList = healthdetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (healthTrackingId != null ? healthTrackingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Healthtracking)) {
            return false;
        }
        Healthtracking other = (Healthtracking) object;
        if ((this.healthTrackingId == null && other.healthTrackingId != null) || (this.healthTrackingId != null && !this.healthTrackingId.equals(other.healthTrackingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Healthtracking[ healthTrackingId=" + healthTrackingId + " ]";
    }
    
}
