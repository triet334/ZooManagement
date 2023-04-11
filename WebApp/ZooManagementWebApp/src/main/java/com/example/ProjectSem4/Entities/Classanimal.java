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
@Table(name = "classanimal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classanimal.findAll", query = "SELECT c FROM Classanimal c"),
    @NamedQuery(name = "Classanimal.findByClassCode", query = "SELECT c FROM Classanimal c WHERE c.classCode = :classCode"),
    @NamedQuery(name = "Classanimal.findByClassName", query = "SELECT c FROM Classanimal c WHERE c.className = :className")})
public class Classanimal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "classCode")
    @NotBlank(message = "Class Code can not blank !")
    private Integer classCode;
    @Size(max = 50)
    @Column(name = "className")
    @NotBlank(message = "Class Name can not blank !")
    private String className;
    @OneToMany(mappedBy = "classCode")
    @JsonIgnore
    private List<Animalfamily> animalfamilyList;
    @OneToMany(mappedBy = "classCode")
    @JsonIgnore
    private List<Employee> employeeList;
    @OneToMany(mappedBy = "classCode")
    @JsonIgnore
    private List<Animal> animalList;

    public Classanimal() {
    }

    public Classanimal(Integer classCode) {
        this.classCode = classCode;
    }

    public Integer getClassCode() {
        return classCode;
    }

    public void setClassCode(Integer classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlTransient
    public List<Animalfamily> getAnimalfamilyList() {
        return animalfamilyList;
    }

    public void setAnimalfamilyList(List<Animalfamily> animalfamilyList) {
        this.animalfamilyList = animalfamilyList;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
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
        hash += (classCode != null ? classCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classanimal)) {
            return false;
        }
        Classanimal other = (Classanimal) object;
        if ((this.classCode == null && other.classCode != null) || (this.classCode != null && !this.classCode.equals(other.classCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Classanimal[ classCode=" + classCode + " ]";
    }
    
}
