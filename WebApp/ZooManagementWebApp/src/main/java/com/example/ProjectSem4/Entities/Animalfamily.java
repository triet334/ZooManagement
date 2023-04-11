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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "animalfamily")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animalfamily.findAll", query = "SELECT a FROM Animalfamily a"),
    @NamedQuery(name = "Animalfamily.findByFamilyCode", query = "SELECT a FROM Animalfamily a WHERE a.familyCode = :familyCode"),
    @NamedQuery(name = "Animalfamily.findByFamilyName", query = "SELECT a FROM Animalfamily a WHERE a.familyName = :familyName")})
public class Animalfamily implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "familyCode")
    private Integer familyCode;
    @Size(max = 150)
    @Column(name = "familyName")
	@NotBlank(message = "Family Name can not blank !")
    private String familyName;
    @JoinColumn(name = "classCode", referencedColumnName = "classCode")
    @ManyToOne
    //@JsonIgnore
	@NotBlank(message = "Class Code can not blank !")
    private Classanimal classCode;
    @OneToMany(mappedBy = "familyCode")
    @JsonIgnore
    private List<Cage> cageList;
    @OneToMany(mappedBy = "familyCode")
    @JsonIgnore
    private List<Animal> animalList;

    public Animalfamily() {
    }

    public Animalfamily(Integer familyCode, String familyName) {
        this.familyCode = familyCode;
        this.familyName = familyName;
    }

    public Animalfamily(Integer familyCode) {
        this.familyCode = familyCode;
    }

    public Integer getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(Integer familyCode) {
        this.familyCode = familyCode;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Classanimal getClassCode() {
        return classCode;
    }

    public void setClassCode(Classanimal classCode) {
        this.classCode = classCode;
    }

    @XmlTransient
    public List<Cage> getCageList() {
        return cageList;
    }

    public void setCageList(List<Cage> cageList) {
        this.cageList = cageList;
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
        hash += (familyCode != null ? familyCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animalfamily)) {
            return false;
        }
        Animalfamily other = (Animalfamily) object;
        if ((this.familyCode == null && other.familyCode != null) || (this.familyCode != null && !this.familyCode.equals(other.familyCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Animalfamily[ familyCode=" + familyCode + " ]";
    }
    
}
