package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Schedule_Model;
import com.example.uniman.Model.Teacher;
import com.example.uniman.Model.Teacher_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teacher_Repository {

    private API api;

    public Teacher_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    public MutableLiveData<Teacher_Model> teacher_modelMutableLiveData(String ma){
        MutableLiveData<Teacher_Model> data = new MutableLiveData<>();
        api.getteacher(ma).enqueue(new Callback<Teacher_Model>() {
            @Override
            public void onResponse(Call<Teacher_Model> call, Response<Teacher_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Teacher_Model> call, Throwable t) {
                Log.e("teacher",t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<Schedule_Model> lecturescheduleMutableLiveData(String mgv, String thu){
        MutableLiveData<Schedule_Model> data = new MutableLiveData<>();
        api.lectureschedule(mgv,thu).enqueue(new Callback<Schedule_Model>() {
            @Override
            public void onResponse(Call<Schedule_Model> call, Response<Schedule_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Schedule_Model> call, Throwable t) {
                Log.e("lectureschedule",t.getMessage());
            }
        });
        return data;
    }

    // hiển ten giang vien spinner
    public MutableLiveData<Teacher_Model> gettengvtheomkhoa_modelMutableLiveData(String makhoa){
        MutableLiveData<Teacher_Model> data = new MutableLiveData<>();
        api.gettengvtheomkhoa(makhoa).enqueue(new Callback<Teacher_Model>() {
            @Override
            public void onResponse(Call<Teacher_Model> call, Response<Teacher_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Teacher_Model> call, Throwable t) {
                Log.e("gettengvtheomkhoa",t.getMessage());
            }
        });
        return data;
    }
}
