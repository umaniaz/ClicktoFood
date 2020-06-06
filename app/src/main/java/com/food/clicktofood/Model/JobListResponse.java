package com.food.clicktofood.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobListResponse {
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
        return "JobListResponse{" +
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

        @SerializedName("TaskID")
        @Expose
        private String taskID;
        @SerializedName("TaskDescription")
        @Expose
        private String taskDescription;
        @SerializedName("PickupLocation")
        @Expose
        private String pickupLocation;
        @SerializedName("PickupTime")
        @Expose
        private String pickupTime;
        @SerializedName("PickupNotes")
        @Expose
        private String pickupNotes;
        @SerializedName("Status")
        @Expose
        private Integer status;
        @SerializedName("CustomerName")
        @Expose
        private String customerName;
        @SerializedName("DropLatitude")
        @Expose
        private Double dropLatitude;
        @SerializedName("DropLongitude")
        @Expose
        private Double dropLongitude;
        @SerializedName("CustomerPhone")
        @Expose
        private String customerPhone;
        @SerializedName("CustomerAddress")
        @Expose
        private String customerAddress;
        @SerializedName("CustomerDescription")
        @Expose
        private String customerDescription;
        @SerializedName("TotalAmount")
        @Expose
        private Double totalAmount;
        @SerializedName("PaymentMode")
        @Expose
        private String paymentMode;
        @SerializedName("AgentID")
        @Expose
        private String AgentID;
        @SerializedName("Categoryname")
        @Expose
        private String Categoryname;


        public String getTaskID() {
            return taskID;
        }

        public void setTaskID(String taskID) {
            this.taskID = taskID;
        }

        public String getTaskDescription() {
            return taskDescription;
        }

        public void setTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
        }

        public String getPickupLocation() {
            return pickupLocation;
        }

        public void setPickupLocation(String pickupLocation) {
            this.pickupLocation = pickupLocation;
        }

        public String getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(String pickupTime) {
            this.pickupTime = pickupTime;
        }

        public String getPickupNotes() {
            return pickupNotes;
        }

        public void setPickupNotes(String pickupNotes) {
            this.pickupNotes = pickupNotes;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Double getDropLatitude() {
            return dropLatitude;
        }

        public void setDropLatitude(Double dropLatitude) {
            this.dropLatitude = dropLatitude;
        }

        public Double getDropLongitude() {
            return dropLongitude;
        }

        public void setDropLongitude(Double dropLongitude) {
            this.dropLongitude = dropLongitude;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
        }

        public String getCustomerDescription() {
            return customerDescription;
        }

        public void setCustomerDescription(String customerDescription) {
            this.customerDescription = customerDescription;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getAgentID() {
            return AgentID;
        }

        public void setAgentID(String agentID) {
            AgentID = agentID;
        }

        public String getCategoryname() {
            return Categoryname;
        }

        public void setCategoryname(String categoryname) {
            Categoryname = categoryname;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "taskID='" + taskID + '\'' +
                    ", taskDescription='" + taskDescription + '\'' +
                    ", pickupLocation='" + pickupLocation + '\'' +
                    ", pickupTime='" + pickupTime + '\'' +
                    ", pickupNotes='" + pickupNotes + '\'' +
                    ", status=" + status +
                    ", customerName='" + customerName + '\'' +
                    ", dropLatitude=" + dropLatitude +
                    ", dropLongitude=" + dropLongitude +
                    ", customerPhone='" + customerPhone + '\'' +
                    ", customerAddress='" + customerAddress + '\'' +
                    ", customerDescription='" + customerDescription + '\'' +
                    ", totalAmount=" + totalAmount +
                    ", paymentMode='" + paymentMode + '\'' +
                    ", AgentID='" + AgentID + '\'' +
                    ", Categoryname='" + Categoryname + '\'' +
                    '}';
        }
    }
}
