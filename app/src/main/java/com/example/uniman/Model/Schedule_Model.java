package com.example.uniman.Model;

import java.util.ArrayList;

public class Schedule_Model {
    private boolean success;
    private String message;
    private ArrayList<Schedule> result;

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

    public ArrayList<Schedule> getResult() {
        return result;
    }

    public void setResult(ArrayList<Schedule> result) {
        this.result = result;
    }
}
