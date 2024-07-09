package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Schedule_Model;
import com.example.uniman.Model.Student_Model;
import com.example.uniman.Model.Uploadingavata_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Schedule_Repository {
    private API api;

    public Schedule_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    // hiển thị lịch hoc
    public MutableLiveData<Schedule_Model> schedule_modelMutableLiveData(String ma, String thu) {
        MutableLiveData<Schedule_Model> data = new MutableLiveData<>();
        api.schedule(ma, thu).enqueue(new Callback<Schedule_Model>() {
            @Override
            public void onResponse(Call<Schedule_Model> call, Response<Schedule_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Schedule_Model> call, Throwable t) {
                Log.e("schedule", t.getMessage());
            }
        });
        return data;
    }

    // thêm sinh vien
    public MutableLiveData<Uploadingavata_Model> insert_modelMutableLiveData(String ma, String name, int gioitinh, String lop, String nganh,
                                                                             String chuyennganh, String hinhanh) {
        MutableLiveData<Uploadingavata_Model> data = new MutableLiveData<>();
        api.insertsv(ma, name, gioitinh, lop, nganh, chuyennganh, hinhanh).enqueue(new Callback<Uploadingavata_Model>() {
            @Override
            public void onResponse(Call<Uploadingavata_Model> call, Response<Uploadingavata_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Uploadingavata_Model> call, Throwable t) {
                Log.e("schedule", t.getMessage());
            }
        });
        return data;
    }

    // hiển thị list class giảng viên dạy
    public MutableLiveData<Schedule_Model> classlistgv_modelMutableLiveData(String mgv) {
        MutableLiveData<Schedule_Model> data = new MutableLiveData<>();
        api.getclassgv(mgv).enqueue(new Callback<Schedule_Model>() {
            @Override
            public void onResponse(Call<Schedule_Model> call, Response<Schedule_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Schedule_Model> call, Throwable t) {
                Log.e("getclassgv", t.getMessage());
            }
        });
        return data;
    }

    // update trangthai lịch học
    public MutableLiveData<Schedule_Model> updateschedule_modelMutableLiveData(String mgv,int id,int tragthai) {
        MutableLiveData<Schedule_Model> data = new MutableLiveData<>();
        api.updateschedule(mgv,id,tragthai).enqueue(new Callback<Schedule_Model>() {
            @Override
            public void onResponse(Call<Schedule_Model> call, Response<Schedule_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Schedule_Model> call, Throwable t) {
                Log.e("updateschedule", t.getMessage());
            }
        });
        return data;
    }
}
