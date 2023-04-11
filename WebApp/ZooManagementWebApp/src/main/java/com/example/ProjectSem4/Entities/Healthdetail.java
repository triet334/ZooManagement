/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ProjectSem4.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "healthdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Healthdetail.findAll", query = "SELECT h FROM Healthdetail h"),
    @NamedQuery(name = "Healthdetail.findById", query = "SELECT h FROM Healthdetail h WHERE h.id = :id"),
    @NamedQuery(name = "Healthdetail.findByEmployeeDescription", query = "SELECT h FROM Healthdetail h WHERE h.employeeDescription = :employeeDescription"),
    @NamedQuery(name = "Healthdetail.findByDoctorNote", query = "SELECT h FROM Healthdetail h WHERE h.doctorNote = :doctorNote"),
    @NamedQuery(name = "Healthdetail.findByDoctorCode", query = "SELECT h FROM Healthdetail h WHERE h.doctorCode = :doctorCode"),
    @NamedQuery(name = "Healthdetail.findByEmployeeCode", query = "SELECT h FROM Healthdetail h WHERE h.employeeCode = :employeeCode"),
    @NamedQuery(name = "Healthdetail.findByTime", query = "SELECT h FROM Healthdetail h WHERE h.time = :time")})
public class Healthdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 350)
    @Column(name = "employeeDescription")
    private String employeeDescription;
    @Size(max = 350)
    @Column(name = "doctorNote")
    private String doctorNote;
    @Size(max = 50)
    @Column(name = "doctorCode")
    private String doctorCode;
    @Size(max = 50)
    @Column(name = "employeeCode")
    private String employeeCode;
    @Size(max = 150)
    @Column(name = "time")
    private String time;
    @JoinColumn(name = "healthid", referencedColumnName = "healthTrackingId")
    @ManyToOne
    //@JsonIgnore
    private Healthtracking healthid;

    public Healthdetail() {
    }

    public Healthdetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Healthtracking getHealthid() {
        return healthid;
    }

    public void setHealthid(Healthtracking healthid) {
        this.healthid = healthid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Healthdetail)) {
            return false;
        }
        Healthdetail other = (Healthdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Healthdetail[ id=" + id + " ]";
    }
    
}
