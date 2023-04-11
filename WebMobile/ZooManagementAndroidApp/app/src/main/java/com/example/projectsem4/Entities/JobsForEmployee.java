package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobsForEmployee implements Serializable {

    @SerializedName("feedTableId")
    private int feedTableId;
    @SerializedName("employeeCode")
    private Employee employeeCode;
    @SerializedName("shiftCode")
    private shiftChange shiftCode;
    @SerializedName("workDate")
    private String workDate;

    public JobsForEmployee() {
    }

    public JobsForEmployee(int feedTableId, Employee employeeCode, shiftChange shiftCode, String workDate) {
        this.feedTableId = feedTableId;
        this.employeeCode = employeeCode;
        this.shiftCode = shiftCode;
        this.workDate = workDate;
    }

    public int getFeedTableId() {
        return feedTableId;
    }

    public void setFeedTableId(int feedTableId) {
        this.feedTableId = feedTableId;
    }

    public Employee getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Employee employeeCode) {
        this.employeeCode = employeeCode;
    }

    public shiftChange getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(shiftChange shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
}
