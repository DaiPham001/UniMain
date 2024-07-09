package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Semester_Model;
import com.example.uniman.Model.Teacher_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_RegisterCourse_Repository {

    private API api;

    public User_RegisterCourse_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    // hiển thị cac môn chua đang ky
    public MutableLiveData<Semester_Model> getmonchuadangky_modelMutableLiveData(int khoa, String namhoc, String hocki){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.getmonchuadangky(khoa,namhoc,hocki).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e("getmonchuadangky",t.getMessage());
            }
        });
        return data;
    }

    // them mon user cac môn  đang ky
    public MutableLiveData<Semester_Model>themmonuser_modelMutableLiveData(String masv,int khoa,String manganh, String namhoc, String hocki,
                                                                             String mamh,String tenmh,String lop,int tinchi,int tongtc,double hocphi,int trangthai,
                                                                             String ngaydk){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.themmonuser(masv,khoa,manganh,namhoc,hocki,mamh,tenmh,lop,tinchi,tongtc,hocphi,trangthai,ngaydk).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e(" themmonuser",t.getMessage());
            }
        });
        return data;
    }

    // hiển thị cac môn da đang ky
    public MutableLiveData<Semester_Model>getmondadangky_modelMutableLiveData(String masv,int khoa, String namhoc, String hocki){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.getmondadangky(masv,khoa,namhoc,hocki).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e("getmondadangky",t.getMessage());
            }
        });
        return data;
    }


}
