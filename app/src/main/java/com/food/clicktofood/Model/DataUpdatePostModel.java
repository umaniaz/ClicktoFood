package com.food.clicktofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUpdatePostModel {

    private String EmpID;

    private String FullName;

    private String email;

    private String phoneNo;

    private String password;

    private String empType;

    private String picture;

    private Integer createdAt;

    private Integer trackID;

    private String firebaseToken;

    private Integer dutyStatus;

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public Integer getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(Integer dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    @Override
    public String toString() {
        return "DataUpdatePostModel{" +
                "EmpID='" + EmpID + '\'' +
                ", FullName='" + FullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                ", empType='" + empType + '\'' +
                ", picture='" + picture + '\'' +
                ", createdAt=" + createdAt +
                ", trackID=" + trackID +
                ", firebaseToken='" + firebaseToken + '\'' +
                ", dutyStatus=" + dutyStatus +
                '}';
    }
}
