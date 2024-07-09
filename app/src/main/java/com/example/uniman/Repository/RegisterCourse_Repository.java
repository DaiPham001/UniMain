package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Semester_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCourse_Repository {

    private API api;

    public RegisterCourse_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    // hiển thị cac môn admin theo ngành
    public MutableLiveData<Semester_Model> getsemester_modelMutableLiveData(int khoa,String namhoc,String hocki){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.getmonadmin(khoa,namhoc,hocki).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e("getsemester",t.getMessage());
            }
        });
        return data;
    }

    // hiển thị cac khoa học
    public MutableLiveData<Semester_Model> getkhoahoc_modelMutableLiveData(String manganh){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.getkhoahoc(manganh).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e(" getkhoahoc",t.getMessage());
            }
        });
        return data;
    }

    // hiển thị cac khoa học
    public MutableLiveData<Semester_Model> getnamhoc_modelMutableLiveData(int khoa){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.getnamhoc(khoa).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e(" getnamhoc",t.getMessage());
            }
        });
        return data;
    }

    // thêm cac môn admin
    public MutableLiveData<Semester_Model> themmonadmin_modelMutableLiveData(int khoa, String manganh, String namhoc, String hocki, String mamh,
                                                                             String tenmh, String tengv, int tinchi,double hocphi){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.themmonadmin(khoa,manganh,namhoc,hocki,mamh,tenmh,tengv,tinchi,hocphi).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e(" getnamhoc",t.getMessage());
            }
        });
        return data;
    }

    // update cac môn admin
    public MutableLiveData<Semester_Model> updatemonadmin_modelMutableLiveData(int id, int khoa, String manganh, String namhoc, String hocki, String mamh,
                                                                             String tenmh, String tengv, int tinchi,double hocphi){
        MutableLiveData<Semester_Model> data = new MutableLiveData<>();
        api.updatemonadmin(id,khoa,manganh,namhoc,hocki,mamh,tenmh,tengv,tinchi,hocphi).enqueue(new Callback<Semester_Model>() {
            @Override
            public void onResponse(Call<Semester_Model> call, Response<Semester_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Semester_Model> call, Throwable t) {
                Log.e(" updatemonadmin",t.getMessage());
            }
        });
        return data;
    }
}
