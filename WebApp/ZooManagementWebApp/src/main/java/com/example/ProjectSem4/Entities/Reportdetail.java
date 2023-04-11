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
@Table(name = "reportdetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportdetail.findAll", query = "SELECT r FROM Reportdetail r"),
    @NamedQuery(name = "Reportdetail.findById", query = "SELECT r FROM Reportdetail r WHERE r.id = :id"),
    @NamedQuery(name = "Reportdetail.findByCheckCage", query = "SELECT r FROM Reportdetail r WHERE r.checkCage = :checkCage"),
    @NamedQuery(name = "Reportdetail.findByFeeding", query = "SELECT r FROM Reportdetail r WHERE r.feeding = :feeding"),
    @NamedQuery(name = "Reportdetail.findByClean", query = "SELECT r FROM Reportdetail r WHERE r.clean = :clean"),
    @NamedQuery(name = "Reportdetail.findByTime", query = "SELECT r FROM Reportdetail r WHERE r.time = :time"),
    @NamedQuery(name = "Reportdetail.findByEmployeeCode", query = "SELECT r FROM Reportdetail r WHERE r.employeeCode = :employeeCode")})
public class Reportdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "checkCage")
    private Boolean checkCage;
    @Column(name = "feeding")
    private Boolean feeding;
    @Column(name = "clean")
    private Boolean clean;
    @Size(max = 150)
    @Column(name = "time")
    private String time;
    @Size(max = 50)
    @Column(name = "employeeCode")
    private String employeeCode;
    @JoinColumn(name = "reportid", referencedColumnName = "reportId")
    @ManyToOne
    //@JsonIgnore
    private Report reportid;

    public Reportdetail() {
    }

    public Reportdetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCheckCage() {
        return checkCage;
    }

    public void setCheckCage(Boolean checkCage) {
        this.checkCage = checkCage;
    }

    public Boolean getFeeding() {
        return feeding;
    }

    public void setFeeding(Boolean feeding) {
        this.feeding = feeding;
    }

    public Boolean getClean() {
        return clean;
    }

    public void setClean(Boolean clean) {
        this.clean = clean;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Report getReportid() {
        return reportid;
    }

    public void setReportid(Report reportid) {
        this.reportid = reportid;
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
        if (!(object instanceof Reportdetail)) {
            return false;
        }
        Reportdetail other = (Reportdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Reportdetail[ id=" + id + " ]";
    }
    
}
