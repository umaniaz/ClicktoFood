package com.food.clicktofood.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptedTaskListResponse {

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
        return "AcceptedTaskListResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public class Branch {

        @SerializedName("ID")
        @Expose
        private Integer iD;
        @SerializedName("BranchCode")
        @Expose
        private String branchCode;
        @SerializedName("PartnerName")
        @Expose
        private String partnerName;
        @SerializedName("BranchName")
        @Expose
        private String branchName;
        @SerializedName("BranchLocation")
        @Expose
        private String branchLocation;
        @SerializedName("Latitude")
        @Expose
        private Double latitude;
        @SerializedName("Longitude")
        @Expose
        private Double longitude;
        @SerializedName("BranchPhone")
        @Expose
        private Object branchPhone;

        public Integer getID() {
            return iD;
        }

        public void setID(Integer iD) {
            this.iD = iD;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBranchLocation() {
            return branchLocation;
        }

        public void setBranchLocation(String branchLocation) {
            this.branchLocation = branchLocation;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Object getBranchPhone() {
            return branchPhone;
        }

        public void setBranchPhone(Object branchPhone) {
            this.branchPhone = branchPhone;
        }

        @Override
        public String toString() {
            return "Branch{" +
                    "iD=" + iD +
                    ", branchCode='" + branchCode + '\'' +
                    ", partnerName='" + partnerName + '\'' +
                    ", branchName='" + branchName + '\'' +
                    ", branchLocation='" + branchLocation + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", branchPhone=" + branchPhone +
                    '}';
        }
    }

    public class Data {

        @SerializedName("assigned")
        @Expose
        private List<Assigned> assigned = null;

        public List<Assigned> getAssigned() {
            return assigned;
        }

        public void setAssigned(List<Assigned> assigned) {
            this.assigned = assigned;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "assigned=" + assigned +
                    '}';
        }
    }

    public class Partner {

        @SerializedName("PartnerID")
        @Expose
        private String partnerID;
        @SerializedName("PartnerName")
        @Expose
        private String partnerName;
        @SerializedName("PartnerType")
        @Expose
        private String partnerType;
        @SerializedName("JoiningDate")
        @Expose
        private String joiningDate;
        @SerializedName("Longitude")
        @Expose
        private Double longitude;
        @SerializedName("Latitude")
        @Expose
        private Double latitude;
        @SerializedName("City")
        @Expose
        private String city;
        @SerializedName("PrimaryDistance")
        @Expose
        private Object primaryDistance;
        @SerializedName("AdditionalDistance")
        @Expose
        private Object additionalDistance;
        @SerializedName("FirebaseToken")
        @Expose
        private Object firebaseToken;
        @SerializedName("CreatedAt")
        @Expose
        private Object createdAt;
        @SerializedName("TrackID")
        @Expose
        private Object trackID;
        @SerializedName("ID")
        @Expose
        private Integer iD;
        @SerializedName("BranchName")
        @Expose
        private Object branchName;
        @SerializedName("BranchCode")
        @Expose
        private Object branchCode;
        @SerializedName("PartnerEmail")
        @Expose
        private String partnerEmail;

        public String getPartnerID() {
            return partnerID;
        }

        public void setPartnerID(String partnerID) {
            this.partnerID = partnerID;
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public String getPartnerType() {
            return partnerType;
        }

        public void setPartnerType(String partnerType) {
            this.partnerType = partnerType;
        }

        public String getJoiningDate() {
            return joiningDate;
        }

        public void setJoiningDate(String joiningDate) {
            this.joiningDate = joiningDate;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getPrimaryDistance() {
            return primaryDistance;
        }

        public void setPrimaryDistance(Object primaryDistance) {
            this.primaryDistance = primaryDistance;
        }

        public Object getAdditionalDistance() {
            return additionalDistance;
        }

        public void setAdditionalDistance(Object additionalDistance) {
            this.additionalDistance = additionalDistance;
        }

        public Object getFirebaseToken() {
            return firebaseToken;
        }

        public void setFirebaseToken(Object firebaseToken) {
            this.firebaseToken = firebaseToken;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getTrackID() {
            return trackID;
        }

        public void setTrackID(Object trackID) {
            this.trackID = trackID;
        }

        public Integer getID() {
            return iD;
        }

        public void setID(Integer iD) {
            this.iD = iD;
        }

        public Object getBranchName() {
            return branchName;
        }

        public void setBranchName(Object branchName) {
            this.branchName = branchName;
        }

        public Object getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(Object branchCode) {
            this.branchCode = branchCode;
        }

        public String getPartnerEmail() {
            return partnerEmail;
        }

        public void setPartnerEmail(String partnerEmail) {
            this.partnerEmail = partnerEmail;
        }

        @Override
        public String toString() {
            return "Partner{" +
                    "partnerID='" + partnerID + '\'' +
                    ", partnerName='" + partnerName + '\'' +
                    ", partnerType='" + partnerType + '\'' +
                    ", joiningDate='" + joiningDate + '\'' +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    ", city='" + city + '\'' +
                    ", primaryDistance=" + primaryDistance +
                    ", additionalDistance=" + additionalDistance +
                    ", firebaseToken=" + firebaseToken +
                    ", createdAt=" + createdAt +
                    ", trackID=" + trackID +
                    ", iD=" + iD +
                    ", branchName=" + branchName +
                    ", branchCode=" + branchCode +
                    ", partnerEmail='" + partnerEmail + '\'' +
                    '}';
        }
    }

    public class Task {

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
        private String agentID;
        @SerializedName("Categoryname")
        @Expose
        private String categoryname;
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
        @SerializedName("DropNotes")
        @Expose
        private Object dropNotes;
        @SerializedName("OrderCreatedTime")
        @Expose
        private String orderCreatedTime;
        @SerializedName("OrderAssignedTime")
        @Expose
        private String orderAssignedTime;
        @SerializedName("OrderPickUpTime")
        @Expose
        private Object orderPickUpTime;
        @SerializedName("OrderDeliveredTime")
        @Expose
        private Object orderDeliveredTime;
        @SerializedName("DistanceTravelled")
        @Expose
        private Object distanceTravelled;
        @SerializedName("WaitngStart")
        @Expose
        private Object waitngStart;
        @SerializedName("WaitingEnd")
        @Expose
        private Object waitingEnd;
        @SerializedName("TotalWaiting")
        @Expose
        private Object totalWaiting;
        @SerializedName("OTP")
        @Expose
        private String oTP;
        @SerializedName("Collected")
        @Expose
        private Integer collected;
        @SerializedName("CollectedStatus")
        @Expose
        private String collectedStatus;
        @SerializedName("mTag")
        @Expose
        private boolean mTag;

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
            return agentID;
        }

        public void setAgentID(String agentID) {
            this.agentID = agentID;
        }

        public String getCategoryname() {
            return categoryname;
        }

        public void setCategoryname(String categoryname) {
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

        public Object getDropNotes() {
            return dropNotes;
        }

        public void setDropNotes(Object dropNotes) {
            this.dropNotes = dropNotes;
        }

        public String getOrderCreatedTime() {
            return orderCreatedTime;
        }

        public void setOrderCreatedTime(String orderCreatedTime) {
            this.orderCreatedTime = orderCreatedTime;
        }

        public String getOrderAssignedTime() {
            return orderAssignedTime;
        }

        public void setOrderAssignedTime(String orderAssignedTime) {
            this.orderAssignedTime = orderAssignedTime;
        }

        public Object getOrderPickUpTime() {
            return orderPickUpTime;
        }

        public void setOrderPickUpTime(Object orderPickUpTime) {
            this.orderPickUpTime = orderPickUpTime;
        }

        public Object getOrderDeliveredTime() {
            return orderDeliveredTime;
        }

        public void setOrderDeliveredTime(Object orderDeliveredTime) {
            this.orderDeliveredTime = orderDeliveredTime;
        }

        public Object getDistanceTravelled() {
            return distanceTravelled;
        }

        public void setDistanceTravelled(Object distanceTravelled) {
            this.distanceTravelled = distanceTravelled;
        }

        public Object getWaitngStart() {
            return waitngStart;
        }

        public void setWaitngStart(Object waitngStart) {
            this.waitngStart = waitngStart;
        }

        public Object getWaitingEnd() {
            return waitingEnd;
        }

        public void setWaitingEnd(Object waitingEnd) {
            this.waitingEnd = waitingEnd;
        }

        public Object getTotalWaiting() {
            return totalWaiting;
        }

        public void setTotalWaiting(Object totalWaiting) {
            this.totalWaiting = totalWaiting;
        }

        public String getOTP() {
            return oTP;
        }

        public void setOTP(String oTP) {
            this.oTP = oTP;
        }

        public Integer getCollected() {
            return collected;
        }

        public void setCollected(Integer collected) {
            this.collected = collected;
        }

        public String getCollectedStatus() {
            return collectedStatus;
        }

        public void setCollectedStatus(String collectedStatus) {
            this.collectedStatus = collectedStatus;
        }

        public boolean getMTag() {
            return mTag;
        }

        public void setMTag(boolean mTag) {
            this.mTag = mTag;
        }

        @Override
        public String toString() {
            return "Task{" +
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
                    ", agentID='" + agentID + '\'' +
                    ", categoryname='" + categoryname + '\'' +
                    ", partnerID='" + partnerID + '\'' +
                    ", createdAt=" + createdAt +
                    ", trackID=" + trackID +
                    ", branchCode='" + branchCode + '\'' +
                    ", createdDate='" + createdDate + '\'' +
                    ", dropNotes=" + dropNotes +
                    ", orderCreatedTime='" + orderCreatedTime + '\'' +
                    ", orderAssignedTime='" + orderAssignedTime + '\'' +
                    ", orderPickUpTime=" + orderPickUpTime +
                    ", orderDeliveredTime=" + orderDeliveredTime +
                    ", distanceTravelled=" + distanceTravelled +
                    ", waitngStart=" + waitngStart +
                    ", waitingEnd=" + waitingEnd +
                    ", totalWaiting=" + totalWaiting +
                    ", oTP='" + oTP + '\'' +
                    ", collected=" + collected +
                    ", collectedStatus='" + collectedStatus + '\'' +
                    ", mTag=" + mTag +
                    '}';
        }
    }

    public class Assigned {

        @SerializedName("task")
        @Expose
        private Task task;
        @SerializedName("partner")
        @Expose
        private Partner partner;
        @SerializedName("branch")
        @Expose
        private Branch branch;

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Partner getPartner() {
            return partner;
        }

        public void setPartner(Partner partner) {
            this.partner = partner;
        }

        public Branch getBranch() {
            return branch;
        }

        public void setBranch(Branch branch) {
            this.branch = branch;
        }

        @Override
        public String toString() {
            return "Assigned{" +
                    "task=" + task +
                    ", partner=" + partner +
                    ", branch=" + branch +
                    '}';
        }
    }
}
