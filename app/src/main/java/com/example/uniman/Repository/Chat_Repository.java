package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Student_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat_Repository {
    private API api;

    public Chat_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }



    // lấy hinh anh chat
    public MutableLiveData<Student_Model> getavatachat_modelMutableLiveData(String ma){
        MutableLiveData<Student_Model> data = new MutableLiveData<>();
        api.getavachat(ma).enqueue(new Callback<Student_Model>() {
            @Override
            public void onResponse(Call<Student_Model> call, Response<Student_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Student_Model> call, Throwable t) {
                Log.e("getavatachat",t.getMessage());
            }
        });
        return data;
    }
}
