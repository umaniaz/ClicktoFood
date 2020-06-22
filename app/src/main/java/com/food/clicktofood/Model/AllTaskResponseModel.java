package com.food.clicktofood.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class AllTaskResponseModel {

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
        return "AllTaskResponseModel{" +
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
        private Object pickupTime;
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
        private Object customerDescription;
        @SerializedName("TotalAmount")
        @Expose
        private Double totalAmount;
        @SerializedName("PaymentMode")
        @Expose
        private String paymentMode;
        @SerializedName("AgentID")
        @Expose
        private String agentID;
        @SerializedName("Categoryname")
        @Expose
        private Object categoryname;
        @SerializedName("PartnerID")
        @Expose
        private String partnerID;
        @SerializedName("CreatedAt")
        @Expose
        private Integer createdAt;
        @SerializedName("TrackID")
        @Expose
        private Integer trackID;
        @SerializedName("BranchCode")
        @Expose
        private String branchCode;
        @SerializedName("CreatedDate")
        @Expose
        private String createdDate;

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

        public Object getPickupTime() {
            return pickupTime;
        }

        public void setPickupTime(Object pickupTime) {
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

        public Object getCustomerDescription() {
            return customerDescription;
        }

        public void setCustomerDescription(Object customerDescription) {
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
            return agentID;
        }

        public void setAgentID(String agentID) {
            this.agentID = agentID;
        }

        public Object getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(Object categoryname) {
            this.categoryname = categoryname;
        }

        public String getPartnerID() {
            return partnerID;
        }

        public void setPartnerID(String partnerID) {
            this.partnerID = partnerID;
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

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "taskID='" + taskID + '\'' +
                    ", taskDescription='" + taskDescription + '\'' +
                    ", pickupLocation='" + pickupLocation + '\'' +
                    ", pickupTime=" + pickupTime +
                    ", pickupNotes='" + pickupNotes + '\'' +
                    ", status=" + status +
                    ", customerName='" + customerName + '\'' +
                    ", dropLatitude=" + dropLatitude +
                    ", dropLongitude=" + dropLongitude +
                    ", customerPhone='" + customerPhone + '\'' +
                    ", customerAddress='" + customerAddress + '\'' +
                    ", customerDescription=" + customerDescription +
                    ", totalAmount=" + totalAmount +
                    ", paymentMode='" + paymentMode + '\'' +
                    ", agentID='" + agentID + '\'' +
                    ", categoryname=" + categoryname +
                    ", partnerID='" + partnerID + '\'' +
                    ", createdAt=" + createdAt +
                    ", trackID=" + trackID +
                    ", branchCode='" + branchCode + '\'' +
                    ", createdDate='" + createdDate + '\'' +
                    '}';
        }
    }
}
