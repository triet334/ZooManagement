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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruava
 */
@Entity
@Table(name = "report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByReportId", query = "SELECT r FROM Report r WHERE r.reportId = :reportId"),
    @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date"),
    @NamedQuery(name = "Report.findByCheckCage", query = "SELECT r FROM Report r WHERE r.checkCage = :checkCage"),
    @NamedQuery(name = "Report.findByFeeding", query = "SELECT r FROM Report r WHERE r.feeding = :feeding"),
    @NamedQuery(name = "Report.findByClean", query = "SELECT r FROM Report r WHERE r.clean = :clean")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reportId")
    private Integer reportId;
    @Size(max = 150)
    @Column(name = "date")
    private String date;
    @Column(name = "checkCage")
    private Boolean checkCage;
    @Column(name = "feeding")
    private Boolean feeding;
    @Column(name = "clean")
    private Boolean clean;
    @OneToMany(mappedBy = "reportid")
    @JsonIgnore
    private List<Reportdetail> reportdetailList;
    @JoinColumn(name = "cageCode", referencedColumnName = "cageCode")
    @ManyToOne
    //@JsonIgnore
    private Cage cageCode;

    public Report() {
    }

    public Report(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @XmlTransient
    public List<Reportdetail> getReportdetailList() {
        return reportdetailList;
    }

    public void setReportdetailList(List<Reportdetail> reportdetailList) {
        this.reportdetailList = reportdetailList;
    }

    public Cage getCageCode() {
        return cageCode;
    }

    public void setCageCode(Cage cageCode) {
        this.cageCode = cageCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.ProjectSem4.Entities.Report[ reportId=" + reportId + " ]";
    }
    
}
