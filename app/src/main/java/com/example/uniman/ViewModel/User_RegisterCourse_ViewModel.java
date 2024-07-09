package com.example.uniman.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uniman.Model.Semester_Model;
import com.example.uniman.Repository.User_RegisterCourse_Repository;


public class User_RegisterCourse_ViewModel extends ViewModel {

    private User_RegisterCourse_Repository user_RegisterCourse_Repository;

    public User_RegisterCourse_ViewModel() {
        this.user_RegisterCourse_Repository = new User_RegisterCourse_Repository();
    }

    public MutableLiveData<Semester_Model> getmonchuadangky(int khoa,String namhoc,String hocki){
        return user_RegisterCourse_Repository.getmonchuadangky_modelMutableLiveData(khoa,namhoc,hocki);
    }

    public MutableLiveData<Semester_Model> themmonuser(String masv,int khoa,String manganh, String namhoc, String hocki,
                                                       String mamh,String tenmh,String lop,int tinchi,int tongtc,double hocphi,int trangthai,
                                                       String ngaydk){
        return user_RegisterCourse_Repository.themmonuser_modelMutableLiveData(masv,khoa,manganh,namhoc,hocki,mamh,tenmh,lop,tinchi,tongtc,hocphi,trangthai,ngaydk);
    }

    public MutableLiveData<Semester_Model> getmondadangky(String masv,int khoa, String namhoc, String hocki){
        return user_RegisterCourse_Repository.getmondadangky_modelMutableLiveData(masv,khoa,namhoc,hocki);
    }


}
