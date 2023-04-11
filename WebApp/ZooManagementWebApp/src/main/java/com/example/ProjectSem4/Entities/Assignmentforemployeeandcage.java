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
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "assignmentforemployeeandcage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assignmentforemployeeandcage.findAll", query = "SELECT a FROM Assignmentforemployeeandcage a"),
    @NamedQuery(name = "Assignmentforemployeeandcage.findByAssignmentCode", query = "SELECT a FROM Assignmentforemployeeandcage a WHERE a.assignmentCode = :assignmentCode")})
public class Assignmentforemployeeandcage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "assignmentCode")
    private Integer assignmentCode;
    @JoinColumn(name = "cageCode", referencedColumnName = "cageCode")
    @ManyToOne(optional = false)
    //@JsonIgnore
    //@NotNull
    //@NotBlank
    private Cage cageCode;
    @JoinColumn(name = "employeeCode", referencedColumnName = "employeeCode")
    @ManyToOne(optional = false)
   // @JsonIgnore
//    @NotNull
    //@NotBlank
    private Employee employeeCode;

    public Assignmentforemployeeandcage() {
    }

    public Assignmentforemployeeandcage(Integer assignmentCode) {
        this.assignmentCode = assignmentCode;
    }

    public Integer getAssignmentCode() {
        return assignmentCode;
    }

    public void setAssignmentCode(Integer assignmentCode) {
        this.assignmentCode = assignmentCode;
    }

    public Cage getCageCode() {
        return cageCode;
    }

    public void setCageCode(Cage cageCode) {
        this.cageCode = cageCode;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignmentCode != null ? assignmentCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assignmentforemployeeandcage)) {
            return false;
        }
        Assignmentforemployeeandcage other = (Assignmentforemployeeandcage) object;
        if ((this.assignmentCode == null && other.assignmentCode != null) || (this.assignmentCode != null && !this.assignmentCode.equals(other.assignmentCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Assignmentforemployeeandcage[ assignmentCode=" + assignmentCode + " ]";
    }
    
}
