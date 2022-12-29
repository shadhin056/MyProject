package com.example.myproject.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerRegResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("userType")
    @Expose
    private String userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}