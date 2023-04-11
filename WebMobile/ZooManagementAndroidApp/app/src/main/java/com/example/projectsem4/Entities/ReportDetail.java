package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReportDetail implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("reportid")
    private Report reportid;

    @SerializedName("checkCage")
    private boolean checkCage;

    @SerializedName("feeding")
    private boolean feeding;

    @SerializedName("clean")
    private boolean clean;

    @SerializedName("time")
    private String time;

    @SerializedName("employeeCode")
    private Employee employeeCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Report getReportid() {
        return reportid;
    }

    public void setReportid(Report reportid) {
        this.reportid = reportid;
    }

    public boolean isCheckCage() {
        return checkCage;
    }

    public void setCheckCage(boolean checkCage) {
        this.checkCage = checkCage;
    }

    public boolean isFeeding() {
        return feeding;
    }

    public void setFeeding(boolean feeding) {
        this.feeding = feeding;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public ReportDetail(int id, Report reportid, boolean checkCage, boolean feeding, boolean clean, String time, Employee employeeCode) {
        this.id = id;
        this.reportid = reportid;
        this.checkCage = checkCage;
        this.feeding = feeding;
        this.clean = clean;
        this.time = time;
        this.employeeCode = employeeCode;
    }

    public ReportDetail() {
    }
}
