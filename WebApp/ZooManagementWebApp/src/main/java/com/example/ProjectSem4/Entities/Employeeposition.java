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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "employeeposition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employeeposition.findAll", query = "SELECT e FROM Employeeposition e"),
    @NamedQuery(name = "Employeeposition.findByPositionCode", query = "SELECT e FROM Employeeposition e WHERE e.positionCode = :positionCode"),
    @NamedQuery(name = "Employeeposition.findByPositionName", query = "SELECT e FROM Employeeposition e WHERE e.positionName = :positionName")})
public class Employeeposition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "positionCode")
    private Integer positionCode;
    @Size(max = 150)
    @Column(name = "positionName")
    private String positionName;
    @OneToMany(mappedBy = "positionCode")
    @JsonIgnore
    private List<Employee> employeeList;

    public Employeeposition() {
    }

    public Employeeposition(Integer positionCode) {
        this.positionCode = positionCode;
    }

    public Integer getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Integer positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionCode != null ? positionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeeposition)) {
            return false;
        }
        Employeeposition other = (Employeeposition) object;
        if ((this.positionCode == null && other.positionCode != null) || (this.positionCode != null && !this.positionCode.equals(other.positionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Employeeposition[ positionCode=" + positionCode + " ]";
    }
    
}
