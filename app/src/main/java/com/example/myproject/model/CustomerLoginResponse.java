package com.example.myproject.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerLoginResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("userType")
    @Expose
    private String userType;

    @SerializedName("fromAccountNoBalance")
    @Expose
    private String fromAccountNoBalance;

    public String getFromAccountNoBalance() {
        return fromAccountNoBalance;
    }

    public void setFromAccountNoBalance(String fromAccountNoBalance) {
        this.fromAccountNoBalance = fromAccountNoBalance;
    }


    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("balance")
    @Expose
    private String balance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}