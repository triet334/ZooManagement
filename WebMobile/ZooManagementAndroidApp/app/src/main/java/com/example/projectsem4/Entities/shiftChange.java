package com.example.projectsem4.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class shiftChange implements Serializable {

    @SerializedName("shiftCode")
    private int shiftCode;

    @SerializedName("shiftTime")
    private String shiftTime;

    public shiftChange() {
    }

    public shiftChange(int shiftCode, String shiftTime) {
        this.shiftCode = shiftCode;
        this.shiftTime = shiftTime;
    }

    public int getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(int shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }
}
