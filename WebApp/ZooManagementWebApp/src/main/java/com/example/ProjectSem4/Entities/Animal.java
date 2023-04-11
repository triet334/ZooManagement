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
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "animal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animal.findAll", query = "SELECT a FROM Animal a"),
    @NamedQuery(name = "Animal.findByAnimalCode", query = "SELECT a FROM Animal a WHERE a.animalCode = :animalCode"),
    @NamedQuery(name = "Animal.findByAnimalName", query = "SELECT a FROM Animal a WHERE a.animalName = :animalName"),
    @NamedQuery(name = "Animal.findByBirthday", query = "SELECT a FROM Animal a WHERE a.birthday = :birthday"),
    @NamedQuery(name = "Animal.findByDateAdded", query = "SELECT a FROM Animal a WHERE a.dateAdded = :dateAdded"),
    @NamedQuery(name = "Animal.findByOriginCountry", query = "SELECT a FROM Animal a WHERE a.originCountry = :originCountry"),
    @NamedQuery(name = "Animal.findByGender", query = "SELECT a FROM Animal a WHERE a.gender = :gender"),
    @NamedQuery(name = "Animal.findByHealthStatus", query = "SELECT a FROM Animal a WHERE a.healthStatus = :healthStatus"),
    @NamedQuery(name = "Animal.findByPhoto", query = "SELECT a FROM Animal a WHERE a.photo = :photo")})
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "animalCode")   
    @NotBlank(message = "Animal Code can not blank !")
    @Size(min = 1, max = 50,message = "Animal Name size must be between 1 and 50 !")
    private String animalCode;
    @Column(name = "animalName")
    @NotBlank(message = "Animal Name can not blank !")
    @Size(min = 2, max = 150, message = "Animal Name size must be between 2 and 150 !")
    private String animalName;
    @Size(max = 150)
    @Column(name = "birthday")
    @NotBlank(message = "Year of Birth can not blank !")
    private String birthday;
    @Size(max = 150)
    @Column(name = "dateAdded")
    @NotBlank(message = "Received Date can not blank !")
    private String dateAdded;
    @Size(max = 50)
    @Column(name = "originCountry")
    @NotBlank(message = "Nationality can not blank !")
    private String originCountry;
    @Column(name = "gender")
    private Boolean gender;
    @Size(max = 250)
    @Column(name = "healthStatus")
    @NotBlank(message = "Health Status can not blank !")
    private String healthStatus;
    @Size(max = 2147483647)
    @Column(name = "photo")
    @NotBlank(message = "Photo can not blank !")
    private String photo;
    @OneToMany(mappedBy = "animalCode")
    @JsonIgnore
    private List<Healthtracking> healthtrackingList;
    @JoinColumn(name = "familyCode", referencedColumnName = "familyCode")
    @ManyToOne
    //@JsonIgnore
    private Animalfamily familyCode;
    @JoinColumn(name = "cageCode", referencedColumnName = "cageCode")
    @ManyToOne
    //@JsonIgnore
    private Cage cageCode;
    @JoinColumn(name = "classCode", referencedColumnName = "classCode")
    @ManyToOne
    //@JsonIgnore
    private Classanimal classCode;

    public Animal() {
    }

    public Animal(String animalCode, String animalName) {
        this.animalCode = animalCode;
        this.animalName = animalName;
    }

    public Animal(String animalCode) {
        this.animalCode = animalCode;
    }

    public String getAnimalCode() {
        return animalCode;
    }

    public void setAnimalCode(String animalCode) {
        this.animalCode = animalCode;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @XmlTransient
    public List<Healthtracking> getHealthtrackingList() {
        return healthtrackingList;
    }

    public void setHealthtrackingList(List<Healthtracking> healthtrackingList) {
        this.healthtrackingList = healthtrackingList;
    }

    public Animalfamily getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(Animalfamily familyCode) {
        this.familyCode = familyCode;
    }

    public Cage getCageCode() {
        return cageCode;
    }

    public void setCageCode(Cage cageCode) {
        this.cageCode = cageCode;
    }

    public Classanimal getClassCode() {
        return classCode;
    }

    public void setClassCode(Classanimal classCode) {
        this.classCode = classCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animalCode != null ? animalCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animal)) {
            return false;
        }
        Animal other = (Animal) object;
        if ((this.animalCode == null && other.animalCode != null) || (this.animalCode != null && !this.animalCode.equals(other.animalCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Animal[ animalCode=" + animalCode + " ]";
    }
    
}
