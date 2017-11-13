package com.example.billing;

import java.io.Serializable;

public class BillingMessage implements Serializable{

    private String userId;
    private int amount;
    public   BillingMessage(String userId,int amount){
        this.userId=userId;
        this.amount=amount;

    }

    public int getAmount() {
        return amount;
    }

    public String getUserId() {
        return userId;
    }


}
