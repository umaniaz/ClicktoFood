package com.food.clicktofood.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ImageUploadResponse {

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
        return "ImageUploadResponse{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public class Data {

        @SerializedName("filename")
        @Expose
        private String filename;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "filename='" + filename + '\'' +
                    '}';
        }
    }
}
