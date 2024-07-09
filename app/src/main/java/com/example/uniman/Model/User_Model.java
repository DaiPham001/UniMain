package com.example.uniman.Model;

import java.util.ArrayList;

public class User_Model {
    private boolean success;
    private String message;
    private ArrayList<User> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<User> getResult() {
        return result;
    }

    public void setResult(ArrayList<User> result) {
        this.result = result;
    }
}
