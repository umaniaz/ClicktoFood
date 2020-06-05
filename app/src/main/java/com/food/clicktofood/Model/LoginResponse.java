package com.food.clicktofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LoginResponse {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public class Data {

        @SerializedName("member")
        @Expose
        private List<Member> member = null;

        public List<Member> getMember() {
            return member;
        }

        public void setMember(List<Member> member) {
            this.member = member;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "member=" + member +
                    '}';
        }
    }

    public class Member {

        @SerializedName("EmpID")
        @Expose
        private String empID;
        @SerializedName("FullName")
        @Expose
        private String fullName;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("PhoneNo")
        @Expose
        private String phoneNo;
        @SerializedName("Password")
        @Expose
        private String password;
        @SerializedName("EmpType")
        @Expose
        private String empType;
        @SerializedName("Picture")
        @Expose
        private String picture;
        @SerializedName("CreatedAt")
        @Expose
        private Integer createdAt;
        @SerializedName("TrackID")
        @Expose
        private Integer trackID;
        @SerializedName("FirebaseToken")
        @Expose
        private String firebaseToken;
        @SerializedName("DutyStatus")
        @Expose
        private Integer dutyStatus;

        public String getEmpID() {
            return empID;
        }

        public void setEmpID(String empID) {
            this.empID = empID;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
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
            return "Member{" +
                    "empID='" + empID + '\'' +
                    ", fullName='" + fullName + '\'' +
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
}
