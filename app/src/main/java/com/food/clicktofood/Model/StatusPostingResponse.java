package com.food.clicktofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class StatusPostingResponse {

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

//    http://18.220.161.232:8083/api/Agents/TaskAcceptOrReject?agentID=EMP201&taskid=T201&status=1
//    http://18.220.161.232:8083/api/Agents/GetTaskAcceptOrReject?agentID=EMP201&taskid=T201&status=1
    @Override
    public String toString() {
        return "StatusPostingResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public class Data {

        @SerializedName("member")
        @Expose
        private Member member;

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
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


    }
}
