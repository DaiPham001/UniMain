package com.example.uniman.Model;

import java.util.ArrayList;

public class Majors_Model {
    private boolean success;
    private String message;
    private ArrayList<Majors> result;

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

    public ArrayList<Majors> getResult() {
        return result;
    }

    public void setResult(ArrayList<Majors> result) {
        this.result = result;
    }
}
