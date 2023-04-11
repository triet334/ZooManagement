package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Report implements Serializable {

    @SerializedName("reportId")
    private int reportId;

    @SerializedName("cageCode")
    private Cage cageCode;

    @SerializedName("date")
    private String date;

    @SerializedName("checkCage")
    private boolean checkCage;

    @SerializedName("feeding")
    private boolean feeding;

    @SerializedName("clean")
    private boolean clean;

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Cage getCageCode() {
        return cageCode;
    }

    public void setCageCode(Cage cageCode) {
        this.cageCode = cageCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Report(int reportId, Cage cageCode, String date, boolean checkCage, boolean feeding, boolean clean) {
        this.reportId = reportId;
        this.cageCode = cageCode;
        this.date = date;
        this.checkCage = checkCage;
        this.feeding = feeding;
        this.clean = clean;
    }

    public Report() {
    }
}
