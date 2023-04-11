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
@Table(name = "shiftChange")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShiftChange.findAll", query = "SELECT s FROM ShiftChange s"),
    @NamedQuery(name = "ShiftChange.findByShiftCode", query = "SELECT s FROM ShiftChange s WHERE s.shiftCode = :shiftCode"),
    @NamedQuery(name = "ShiftChange.findByShiftTime", query = "SELECT s FROM ShiftChange s WHERE s.shiftTime = :shiftTime")})
public class ShiftChange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "shiftCode")
    private Integer shiftCode;
    @Size(max = 350)
    @Column(name = "shiftTime")
    private String shiftTime;
    @OneToMany(mappedBy = "shiftCode")
    @JsonIgnore
    private List<Jobsforemployee> jobsforemployeeList;

    public ShiftChange() {
    }

    public ShiftChange(Integer shiftCode) {
        this.shiftCode = shiftCode;
    }

    public Integer getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(Integer shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    @XmlTransient
    public List<Jobsforemployee> getJobsforemployeeList() {
        return jobsforemployeeList;
    }

    public void setJobsforemployeeList(List<Jobsforemployee> jobsforemployeeList) {
        this.jobsforemployeeList = jobsforemployeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftCode != null ? shiftCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShiftChange)) {
            return false;
        }
        ShiftChange other = (ShiftChange) object;
        if ((this.shiftCode == null && other.shiftCode != null) || (this.shiftCode != null && !this.shiftCode.equals(other.shiftCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.ShiftChange[ shiftCode=" + shiftCode + " ]";
    }
    
}
