package com.example.uniman.Model;

import java.util.ArrayList;

public class Student_Model {
    private boolean success;
    private String message;
    private ArrayList<Student> result;

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

    public ArrayList<Student> getResult() {
        return result;
    }

    public void setResult(ArrayList<Student> result) {
        this.result = result;
    }
}
