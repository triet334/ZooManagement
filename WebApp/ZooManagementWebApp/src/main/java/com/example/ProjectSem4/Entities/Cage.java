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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cage.findAll", query = "SELECT c FROM Cage c"),
    @NamedQuery(name = "Cage.findByCageCode", query = "SELECT c FROM Cage c WHERE c.cageCode = :cageCode"),
    @NamedQuery(name = "Cage.findByCageName", query = "SELECT c FROM Cage c WHERE c.cageName = :cageName"),
    @NamedQuery(name = "Cage.findByMaxEmployee", query = "SELECT c FROM Cage c WHERE c.maxEmployee = :maxEmployee"),
    @NamedQuery(name = "Cage.findByMaxAnimal", query = "SELECT c FROM Cage c WHERE c.maxAnimal = :maxAnimal")})
public class Cage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cageCode")
    @NotBlank(message = "Cage Code can not blank !")
    private Integer cageCode;
    @Size(max = 50)
    @Column(name = "cageName")
    @NotBlank(message = "Cage Name can not blank !")
    private String cageName;
    @Column(name = "maxEmployee")
    private Integer maxEmployee;
    @Column(name = "maxAnimal")
    private Integer maxAnimal;
    @JoinColumn(name = "familyCode", referencedColumnName = "familyCode")
    @ManyToOne
    //@JsonIgnore
    @NotBlank
    private Animalfamily familyCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cageCode")
    @JsonIgnore
    private List<Assignmentforemployeeandcage> assignmentforemployeeandcageList;
    @OneToMany(mappedBy = "cageCode")
    @JsonIgnore
    private List<Report> reportList;
    @OneToMany(mappedBy = "cageCode")
    @JsonIgnore
    private List<Animal> animalList;

    public Cage() {
    }

    public Cage(Integer cageCode, String cageName) {
        this.cageCode = cageCode;
        this.cageName = cageName;
    }

    public Cage(Integer cageCode) {
        this.cageCode = cageCode;
    }

    public Integer getCageCode() {
        return cageCode;
    }

    public void setCageCode(Integer cageCode) {
        this.cageCode = cageCode;
    }

    public String getCageName() {
        return cageName;
    }

    public void setCageName(String cageName) {
        this.cageName = cageName;
    }

    public Integer getMaxEmployee() {
        return maxEmployee;
    }

    public void setMaxEmployee(Integer maxEmployee) {
        this.maxEmployee = maxEmployee;
    }

    public Integer getMaxAnimal() {
        return maxAnimal;
    }

    public void setMaxAnimal(Integer maxAnimal) {
        this.maxAnimal = maxAnimal;
    }

    public Animalfamily getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(Animalfamily familyCode) {
        this.familyCode = familyCode;
    }

    @XmlTransient
    public List<Assignmentforemployeeandcage> getAssignmentforemployeeandcageList() {
        return assignmentforemployeeandcageList;
    }

    public void setAssignmentforemployeeandcageList(List<Assignmentforemployeeandcage> assignmentforemployeeandcageList) {
        this.assignmentforemployeeandcageList = assignmentforemployeeandcageList;
    }

    @XmlTransient
    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    @XmlTransient
    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cageCode != null ? cageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cage)) {
            return false;
        }
        Cage other = (Cage) object;
        if ((this.cageCode == null && other.cageCode != null) || (this.cageCode != null && !this.cageCode.equals(other.cageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Cage[ cageCode=" + cageCode + " ]";
    }
    
}
