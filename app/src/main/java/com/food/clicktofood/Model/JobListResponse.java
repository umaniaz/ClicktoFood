package com.food.clicktofood.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class JobListResponse{
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
        @SerializedName("assigned")
        @Expose
        private Assigned assigned;
        @SerializedName("CurrentStatus")
        @Expose
        private Integer currentStatus;

        public List<Member> getMember() {
            return member;
        }

        public void setMember(List<Member> member) {
            this.member = member;
        }

        public Assigned getAssigned() {
            return assigned;
        }

        public void setAssigned(Assigned assigned) {
            this.assigned = assigned;
        }

        public Integer getCurrentStatus() {
            return currentStatus;
        }

        public void setCurrentStatus(Integer currentStatus) {
            this.currentStatus = currentStatus;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "member=" + member +
                    ", assigned=" + assigned +
                    ", currentStatus=" + currentStatus +
                    '}';
        }
    }

    public class M {

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
            return "M{" +
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

    public class M_ {

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
            return "M_{" +
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

    public class Member {

        @SerializedName("n")
        @Expose
        private N n;
        @SerializedName("m")
        @Expose
        private M m;
        @SerializedName("o")
        @Expose
        private O o;

        public N getN() {
            return n;
        }

        public void setN(N n) {
            this.n = n;
        }

        public M getM() {
            return m;
        }

        public void setM(M m) {
            this.m = m;
        }

        public O getO() {
            return o;
        }

        public void setO(O o) {
            this.o = o;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "n=" + n +
                    ", m=" + m +
                    ", o=" + o +
                    '}';
        }
    }

    public class O {

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
        private Object BranchPhone;

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
            return BranchPhone;
        }

        public void setBranchPhone(Object branchPhone) {
            BranchPhone = branchPhone;
        }

        @Override
        public String toString() {
            return "O{" +
                    "iD=" + iD +
                    ", branchCode='" + branchCode + '\'' +
                    ", partnerName='" + partnerName + '\'' +
                    ", branchName='" + branchName + '\'' +
                    ", branchLocation='" + branchLocation + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", BranchPhone=" + BranchPhone +
                    '}';
        }
    }

    public class N {

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
        private Object agentID;
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
        @SerializedName("DropNotes")
        @Expose
        private Object dropNotes;

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

        public Object getAgentID() {
            return agentID;
        }

        public void setAgentID(Object agentID) {
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

        public Object getDropNotes() {
            return dropNotes;
        }

        public void setDropNotes(Object dropNotes) {
            this.dropNotes = dropNotes;
        }

        @Override
        public String toString() {
            return "N{" +
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
                    ", agentID=" + agentID +
                    ", categoryname='" + categoryname + '\'' +
                    ", partnerID='" + partnerID + '\'' +
                    ", createdAt=" + createdAt +
                    ", trackID=" + trackID +
                    ", branchCode='" + branchCode + '\'' +
                    ", dropNotes=" + dropNotes +
                    '}';
        }
    }

    public class N_ {

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
        @SerializedName("DropNotes")
        @Expose
        private Object dropNotes;

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

        public Object getDropNotes() {
            return dropNotes;
        }

        public void setDropNotes(Object dropNotes) {
            this.dropNotes = dropNotes;
        }

        @Override
        public String toString() {
            return "N_{" +
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
                    ", dropNotes=" + dropNotes +
                    '}';
        }
    }

    public class Assigned {

        @SerializedName("n")
        @Expose
        private N_ n;
        @SerializedName("m")
        @Expose
        private M_ m;
        @SerializedName("o")
        @Expose
        private O_ o;

        public N_ getN() {
            return n;
        }

        public void setN(N_ n) {
            this.n = n;
        }

        public M_ getM() {
            return m;
        }

        public void setM(M_ m) {
            this.m = m;
        }

        public O_ getO() {
            return o;
        }

        public void setO(O_ o) {
            this.o = o;
        }

        @Override
        public String toString() {
            return "Assigned{" +
                    "n=" + n +
                    ", m=" + m +
                    ", o=" + o +
                    '}';
        }
    }

    public class O_ {

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
        private Object BranchPhone;

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
            return BranchPhone;
        }

        public void setBranchPhone(Object branchPhone) {
            BranchPhone = branchPhone;
        }

        @Override
        public String toString() {
            return "O_{" +
                    "iD=" + iD +
                    ", branchCode='" + branchCode + '\'' +
                    ", partnerName='" + partnerName + '\'' +
                    ", branchName='" + branchName + '\'' +
                    ", branchLocation='" + branchLocation + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", BranchPhone=" + BranchPhone +
                    '}';
        }
    }
}

// previous ok
//public class JobListResponse{
//
//    @SerializedName("data")
//    @Expose
//    private Data data;
//    @SerializedName("code")
//    @Expose
//    private Integer code;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("isSuccess")
//    @Expose
//    private Boolean isSuccess;
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Boolean getIsSuccess() {
//        return isSuccess;
//    }
//
//    public void setIsSuccess(Boolean isSuccess) {
//        this.isSuccess = isSuccess;
//    }
//
//    @Override
//    public String toString() {
//        return "JobListResponse{" +
//                "data=" + data +
//                ", code=" + code +
//                ", message='" + message + '\'' +
//                ", isSuccess=" + isSuccess +
//                '}';
//    }
//
//    public class Member {
//
//        @SerializedName("TaskID")
//        @Expose
//        private String taskID;
//        @SerializedName("TaskDescription")
//        @Expose
//        private String taskDescription;
//        @SerializedName("PickupLocation")
//        @Expose
//        private String pickupLocation;
//        @SerializedName("PickupTime")
//        @Expose
//        private String pickupTime;
//        @SerializedName("PickupNotes")
//        @Expose
//        private String pickupNotes;
//        @SerializedName("Status")
//        @Expose
//        private Integer status;
//        @SerializedName("CustomerName")
//        @Expose
//        private String customerName;
//        @SerializedName("DropLatitude")
//        @Expose
//        private Double dropLatitude;
//        @SerializedName("DropLongitude")
//        @Expose
//        private Double dropLongitude;
//        @SerializedName("CustomerPhone")
//        @Expose
//        private String customerPhone;
//        @SerializedName("CustomerAddress")
//        @Expose
//        private String customerAddress;
//        @SerializedName("CustomerDescription")
//        @Expose
//        private String customerDescription;
//        @SerializedName("TotalAmount")
//        @Expose
//        private Double totalAmount;
//        @SerializedName("PaymentMode")
//        @Expose
//        private String paymentMode;
//        @SerializedName("AgentID")
//        @Expose
//        private String agentID;
//        @SerializedName("Categoryname")
//        @Expose
//        private String categoryname;
//
//        public String getTaskID() {
//            return taskID;
//        }
//
//        public void setTaskID(String taskID) {
//            this.taskID = taskID;
//        }
//
//        public String getTaskDescription() {
//            return taskDescription;
//        }
//
//        public void setTaskDescription(String taskDescription) {
//            this.taskDescription = taskDescription;
//        }
//
//        public String getPickupLocation() {
//            return pickupLocation;
//        }
//
//        public void setPickupLocation(String pickupLocation) {
//            this.pickupLocation = pickupLocation;
//        }
//
//        public String getPickupTime() {
//            return pickupTime;
//        }
//
//        public void setPickupTime(String pickupTime) {
//            this.pickupTime = pickupTime;
//        }
//
//        public String getPickupNotes() {
//            return pickupNotes;
//        }
//
//        public void setPickupNotes(String pickupNotes) {
//            this.pickupNotes = pickupNotes;
//        }
//
//        public Integer getStatus() {
//            return status;
//        }
//
//        public void setStatus(Integer status) {
//            this.status = status;
//        }
//
//        public String getCustomerName() {
//            return customerName;
//        }
//
//        public void setCustomerName(String customerName) {
//            this.customerName = customerName;
//        }
//
//        public Double getDropLatitude() {
//            return dropLatitude;
//        }
//
//        public void setDropLatitude(Double dropLatitude) {
//            this.dropLatitude = dropLatitude;
//        }
//
//        public Double getDropLongitude() {
//            return dropLongitude;
//        }
//
//        public void setDropLongitude(Double dropLongitude) {
//            this.dropLongitude = dropLongitude;
//        }
//
//        public String getCustomerPhone() {
//            return customerPhone;
//        }
//
//        public void setCustomerPhone(String customerPhone) {
//            this.customerPhone = customerPhone;
//        }
//
//        public String getCustomerAddress() {
//            return customerAddress;
//        }
//
//        public void setCustomerAddress(String customerAddress) {
//            this.customerAddress = customerAddress;
//        }
//
//        public String getCustomerDescription() {
//            return customerDescription;
//        }
//
//        public void setCustomerDescription(String customerDescription) {
//            this.customerDescription = customerDescription;
//        }
//
//        public Double getTotalAmount() {
//            return totalAmount;
//        }
//
//        public void setTotalAmount(Double totalAmount) {
//            this.totalAmount = totalAmount;
//        }
//
//        public String getPaymentMode() {
//            return paymentMode;
//        }
//
//        public void setPaymentMode(String paymentMode) {
//            this.paymentMode = paymentMode;
//        }
//
//        public String getAgentID() {
//            return agentID;
//        }
//
//        public void setAgentID(String agentID) {
//            this.agentID = agentID;
//        }
//
//        public String getCategoryname() {
//            return categoryname;
//        }
//
//        public void setCategoryname(String categoryname) {
//            this.categoryname = categoryname;
//        }
//
//        @Override
//        public String toString() {
//            return "Member{" +
//                    "taskID='" + taskID + '\'' +
//                    ", taskDescription='" + taskDescription + '\'' +
//                    ", pickupLocation='" + pickupLocation + '\'' +
//                    ", pickupTime='" + pickupTime + '\'' +
//                    ", pickupNotes='" + pickupNotes + '\'' +
//                    ", status=" + status +
//                    ", customerName='" + customerName + '\'' +
//                    ", dropLatitude=" + dropLatitude +
//                    ", dropLongitude=" + dropLongitude +
//                    ", customerPhone='" + customerPhone + '\'' +
//                    ", customerAddress='" + customerAddress + '\'' +
//                    ", customerDescription='" + customerDescription + '\'' +
//                    ", totalAmount=" + totalAmount +
//                    ", paymentMode='" + paymentMode + '\'' +
//                    ", agentID='" + agentID + '\'' +
//                    ", categoryname=" + categoryname +
//                    '}';
//        }
//    }
//
//    public class Assigned {
//
//        @SerializedName("TaskID")
//        @Expose
//        private String taskID;
//        @SerializedName("TaskDescription")
//        @Expose
//        private String taskDescription;
//        @SerializedName("PickupLocation")
//        @Expose
//        private String pickupLocation;
//        @SerializedName("PickupTime")
//        @Expose
//        private String pickupTime;
//        @SerializedName("PickupNotes")
//        @Expose
//        private String pickupNotes;
//        @SerializedName("Status")
//        @Expose
//        private Integer status;
//        @SerializedName("CustomerName")
//        @Expose
//        private String customerName;
//        @SerializedName("DropLatitude")
//        @Expose
//        private Double dropLatitude;
//        @SerializedName("DropLongitude")
//        @Expose
//        private Double dropLongitude;
//        @SerializedName("CustomerPhone")
//        @Expose
//        private String customerPhone;
//        @SerializedName("CustomerAddress")
//        @Expose
//        private String customerAddress;
//        @SerializedName("CustomerDescription")
//        @Expose
//        private String customerDescription;
//        @SerializedName("TotalAmount")
//        @Expose
//        private Double totalAmount;
//        @SerializedName("PaymentMode")
//        @Expose
//        private String paymentMode;
//        @SerializedName("AgentID")
//        @Expose
//        private String agentID;
//        @SerializedName("Categoryname")
//        @Expose
//        private Object categoryname;
//
//        public String getTaskID() {
//            return taskID;
//        }
//
//        public void setTaskID(String taskID) {
//            this.taskID = taskID;
//        }
//
//        public String getTaskDescription() {
//            return taskDescription;
//        }
//
//        public void setTaskDescription(String taskDescription) {
//            this.taskDescription = taskDescription;
//        }
//
//        public String getPickupLocation() {
//            return pickupLocation;
//        }
//
//        public void setPickupLocation(String pickupLocation) {
//            this.pickupLocation = pickupLocation;
//        }
//
//        public String getPickupTime() {
//            return pickupTime;
//        }
//
//        public void setPickupTime(String pickupTime) {
//            this.pickupTime = pickupTime;
//        }
//
//        public String getPickupNotes() {
//            return pickupNotes;
//        }
//
//        public void setPickupNotes(String pickupNotes) {
//            this.pickupNotes = pickupNotes;
//        }
//
//        public Integer getStatus() {
//            return status;
//        }
//
//        public void setStatus(Integer status) {
//            this.status = status;
//        }
//
//        public String getCustomerName() {
//            return customerName;
//        }
//
//        public void setCustomerName(String customerName) {
//            this.customerName = customerName;
//        }
//
//        public Double getDropLatitude() {
//            return dropLatitude;
//        }
//
//        public void setDropLatitude(Double dropLatitude) {
//            this.dropLatitude = dropLatitude;
//        }
//
//        public Double getDropLongitude() {
//            return dropLongitude;
//        }
//
//        public void setDropLongitude(Double dropLongitude) {
//            this.dropLongitude = dropLongitude;
//        }
//
//        public String getCustomerPhone() {
//            return customerPhone;
//        }
//
//        public void setCustomerPhone(String customerPhone) {
//            this.customerPhone = customerPhone;
//        }
//
//        public String getCustomerAddress() {
//            return customerAddress;
//        }
//
//        public void setCustomerAddress(String customerAddress) {
//            this.customerAddress = customerAddress;
//        }
//
//        public String getCustomerDescription() {
//            return customerDescription;
//        }
//
//        public void setCustomerDescription(String customerDescription) {
//            this.customerDescription = customerDescription;
//        }
//
//        public Double getTotalAmount() {
//            return totalAmount;
//        }
//
//        public void setTotalAmount(Double totalAmount) {
//            this.totalAmount = totalAmount;
//        }
//
//        public String getPaymentMode() {
//            return paymentMode;
//        }
//
//        public void setPaymentMode(String paymentMode) {
//            this.paymentMode = paymentMode;
//        }
//
//        public String getAgentID() {
//            return agentID;
//        }
//
//        public void setAgentID(String agentID) {
//            this.agentID = agentID;
//        }
//
//        public Object getCategoryname() {
//            return categoryname;
//        }
//
//        public void setCategoryname(Object categoryname) {
//            this.categoryname = categoryname;
//        }
//
//        @Override
//        public String toString() {
//            return "Assigned{" +
//                    "taskID='" + taskID + '\'' +
//                    ", taskDescription='" + taskDescription + '\'' +
//                    ", pickupLocation='" + pickupLocation + '\'' +
//                    ", pickupTime='" + pickupTime + '\'' +
//                    ", pickupNotes='" + pickupNotes + '\'' +
//                    ", status=" + status +
//                    ", customerName='" + customerName + '\'' +
//                    ", dropLatitude=" + dropLatitude +
//                    ", dropLongitude=" + dropLongitude +
//                    ", customerPhone='" + customerPhone + '\'' +
//                    ", customerAddress='" + customerAddress + '\'' +
//                    ", customerDescription='" + customerDescription + '\'' +
//                    ", totalAmount=" + totalAmount +
//                    ", paymentMode='" + paymentMode + '\'' +
//                    ", agentID='" + agentID + '\'' +
//                    ", categoryname=" + categoryname +
//                    '}';
//        }
//    }
//
//    public class Data {
//
//        @SerializedName("member")
//        @Expose
//        private List<Member> member = null;
//        @SerializedName("assigned")
//        @Expose
//        private Assigned assigned;
//        @SerializedName("CurrentStatus")
//        @Expose
//        private Integer currentStatus;
//
//        public List<Member> getMember() {
//            return member;
//        }
//
//        public void setMember(List<Member> member) {
//            this.member = member;
//        }
//
//        public Assigned getAssigned() {
//            return assigned;
//        }
//
//        public void setAssigned(Assigned assigned) {
//            this.assigned = assigned;
//        }
//
//        public Integer getCurrentStatus() {
//            return currentStatus;
//        }
//
//        public void setCurrentStatus(Integer currentStatus) {
//            this.currentStatus = currentStatus;
//        }
//
//        @Override
//        public String toString() {
//            return "Data{" +
//                    "member=" + member +
//                    ", assigned=" + assigned +
//                    ", currentStatus=" + currentStatus +
//                    '}';
//        }
//    }
//
//}



//public class JobListResponse {
//    @SerializedName("data")
//    @Expose
//    private Data data;
//    @SerializedName("code")
//    @Expose
//    private Integer code;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("isSuccess")
//    @Expose
//    private Boolean isSuccess;
//
//    public Data getData() {
//        return data;
//    }
//
//    public void setData(Data data) {
//        this.data = data;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Boolean getIsSuccess() {
//        return isSuccess;
//    }
//
//    public void setIsSuccess(Boolean isSuccess) {
//        this.isSuccess = isSuccess;
//    }
//
//    @Override
//    public String toString() {
//        return "JobListResponse{" +
//                "data=" + data +
//                ", code=" + code +
//                ", message='" + message + '\'' +
//                ", isSuccess=" + isSuccess +
//                '}';
//    }
//
//    public class Data {
//
//        @SerializedName("member")
//        @Expose
//        private List<Member> member = null;
//
//        public List<Member> getMember() {
//            return member;
//        }
//
//        public void setMember(List<Member> member) {
//            this.member = member;
//        }
//
//        @Override
//        public String toString() {
//            return "Data{" +
//                    "member=" + member +
//                    '}';
//        }
//    }
//    public class Member {
//
//        @SerializedName("TaskID")
//        @Expose
//        private String taskID;
//        @SerializedName("TaskDescription")
//        @Expose
//        private String taskDescription;
//        @SerializedName("PickupLocation")
//        @Expose
//        private String pickupLocation;
//        @SerializedName("PickupTime")
//        @Expose
//        private String pickupTime;
//        @SerializedName("PickupNotes")
//        @Expose
//        private String pickupNotes;
//        @SerializedName("Status")
//        @Expose
//        private Integer status;
//        @SerializedName("CustomerName")
//        @Expose
//        private String customerName;
//        @SerializedName("DropLatitude")
//        @Expose
//        private Double dropLatitude;
//        @SerializedName("DropLongitude")
//        @Expose
//        private Double dropLongitude;
//        @SerializedName("CustomerPhone")
//        @Expose
//        private String customerPhone;
//        @SerializedName("CustomerAddress")
//        @Expose
//        private String customerAddress;
//        @SerializedName("CustomerDescription")
//        @Expose
//        private String customerDescription;
//        @SerializedName("TotalAmount")
//        @Expose
//        private Double totalAmount;
//        @SerializedName("PaymentMode")
//        @Expose
//        private String paymentMode;
//        @SerializedName("AgentID")
//        @Expose
//        private String AgentID;
//        @SerializedName("Categoryname")
//        @Expose
//        private String Categoryname;
//
//
//        public String getTaskID() {
//            return taskID;
//        }
//
//        public void setTaskID(String taskID) {
//            this.taskID = taskID;
//        }
//
//        public String getTaskDescription() {
//            return taskDescription;
//        }
//
//        public void setTaskDescription(String taskDescription) {
//            this.taskDescription = taskDescription;
//        }
//
//        public String getPickupLocation() {
//            return pickupLocation;
//        }
//
//        public void setPickupLocation(String pickupLocation) {
//            this.pickupLocation = pickupLocation;
//        }
//
//        public String getPickupTime() {
//            return pickupTime;
//        }
//
//        public void setPickupTime(String pickupTime) {
//            this.pickupTime = pickupTime;
//        }
//
//        public String getPickupNotes() {
//            return pickupNotes;
//        }
//
//        public void setPickupNotes(String pickupNotes) {
//            this.pickupNotes = pickupNotes;
//        }
//
//        public Integer getStatus() {
//            return status;
//        }
//
//        public void setStatus(Integer status) {
//            this.status = status;
//        }
//
//        public String getCustomerName() {
//            return customerName;
//        }
//
//        public void setCustomerName(String customerName) {
//            this.customerName = customerName;
//        }
//
//        public Double getDropLatitude() {
//            return dropLatitude;
//        }
//
//        public void setDropLatitude(Double dropLatitude) {
//            this.dropLatitude = dropLatitude;
//        }
//
//        public Double getDropLongitude() {
//            return dropLongitude;
//        }
//
//        public void setDropLongitude(Double dropLongitude) {
//            this.dropLongitude = dropLongitude;
//        }
//
//        public String getCustomerPhone() {
//            return customerPhone;
//        }
//
//        public void setCustomerPhone(String customerPhone) {
//            this.customerPhone = customerPhone;
//        }
//
//        public String getCustomerAddress() {
//            return customerAddress;
//        }
//
//        public void setCustomerAddress(String customerAddress) {
//            this.customerAddress = customerAddress;
//        }
//
//        public String getCustomerDescription() {
//            return customerDescription;
//        }
//
//        public void setCustomerDescription(String customerDescription) {
//            this.customerDescription = customerDescription;
//        }
//
//        public Double getTotalAmount() {
//            return totalAmount;
//        }
//
//        public void setTotalAmount(Double totalAmount) {
//            this.totalAmount = totalAmount;
//        }
//
//        public String getPaymentMode() {
//            return paymentMode;
//        }
//
//        public void setPaymentMode(String paymentMode) {
//            this.paymentMode = paymentMode;
//        }
//
//        public String getAgentID() {
//            return AgentID;
//        }
//
//        public void setAgentID(String agentID) {
//            AgentID = agentID;
//        }
//
//        public String getCategoryname() {
//            return Categoryname;
//        }
//
//        public void setCategoryname(String categoryname) {
//            Categoryname = categoryname;
//        }
//
//        @Override
//        public String toString() {
//            return "Member{" +
//                    "taskID='" + taskID + '\'' +
//                    ", taskDescription='" + taskDescription + '\'' +
//                    ", pickupLocation='" + pickupLocation + '\'' +
//                    ", pickupTime='" + pickupTime + '\'' +
//                    ", pickupNotes='" + pickupNotes + '\'' +
//                    ", status=" + status +
//                    ", customerName='" + customerName + '\'' +
//                    ", dropLatitude=" + dropLatitude +
//                    ", dropLongitude=" + dropLongitude +
//                    ", customerPhone='" + customerPhone + '\'' +
//                    ", customerAddress='" + customerAddress + '\'' +
//                    ", customerDescription='" + customerDescription + '\'' +
//                    ", totalAmount=" + totalAmount +
//                    ", paymentMode='" + paymentMode + '\'' +
//                    ", AgentID='" + AgentID + '\'' +
//                    ", Categoryname='" + Categoryname + '\'' +
//                    '}';
//        }
//    }
//}
