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
import javax.validation.constraints.FutureOrPresent;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "jobsforemployee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobsforemployee.findAll", query = "SELECT j FROM Jobsforemployee j"),
    @NamedQuery(name = "Jobsforemployee.findByFeedTableId", query = "SELECT j FROM Jobsforemployee j WHERE j.feedTableId = :feedTableId"),
    @NamedQuery(name = "Jobsforemployee.findByWorkDate", query = "SELECT j FROM Jobsforemployee j WHERE j.workDate = :workDate")})
public class Jobsforemployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedTableId")
    private Integer feedTableId;
    @Size(max = 50)
    @Column(name = "workDate")
    private String workDate;
    @JoinColumn(name = "employeeCode", referencedColumnName = "employeeCode")
    @ManyToOne
    //@JsonIgnore
    private Employee employeeCode;
    @JoinColumn(name = "shiftCode", referencedColumnName = "shiftCode")
    @ManyToOne
    //@JsonIgnore
    private ShiftChange shiftCode;

    public Jobsforemployee(Integer feedTableId, String workDate) {
        this.feedTableId = feedTableId;
        this.workDate = workDate;
    }

    public Jobsforemployee(Employee employeeCode, ShiftChange shiftCode) {
        this.employeeCode = employeeCode;
        this.shiftCode = shiftCode;
    }

    public Jobsforemployee(Employee employeeCode, ShiftChange shiftCode, String workDate) {
        this.employeeCode = employeeCode;
        this.shiftCode = shiftCode;
         this.workDate = workDate;
    }
    
    

    public Jobsforemployee() {
    }

    public Jobsforemployee(Integer feedTableId) {
        this.feedTableId = feedTableId;
    }

    public Integer getFeedTableId() {
        return feedTableId;
    }

    public void setFeedTableId(Integer feedTableId) {
        this.feedTableId = feedTableId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public ShiftChange getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(ShiftChange shiftCode) {
        this.shiftCode = shiftCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedTableId != null ? feedTableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobsforemployee)) {
            return false;
        }
        Jobsforemployee other = (Jobsforemployee) object;
        if ((this.feedTableId == null && other.feedTableId != null) || (this.feedTableId != null && !this.feedTableId.equals(other.feedTableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Jobsforemployee[ feedTableId=" + feedTableId + " ]";
    }
    
}
