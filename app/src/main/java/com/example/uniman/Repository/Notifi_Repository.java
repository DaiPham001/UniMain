package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Model.Notifi_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notifi_Repository {
    private API api;

    public Notifi_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    public MutableLiveData<Notifi_Model> notifi_modelMutableLiveData(){
        MutableLiveData<Notifi_Model> data = new MutableLiveData<>();
        api.getnotifi().enqueue(new Callback<Notifi_Model>() {
            @Override
            public void onResponse(Call<Notifi_Model> call, Response<Notifi_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Notifi_Model> call, Throwable t) {
                Log.e("notifi",t.getMessage());
            }
        });
        return data;
    }


    public MutableLiveData<Notifi_Model> themnotifi_modelMutableLiveData(String title,String body){
        MutableLiveData<Notifi_Model> data = new MutableLiveData<>();
        api.themnotifi(title,body).enqueue(new Callback<Notifi_Model>() {
            @Override
            public void onResponse(Call<Notifi_Model> call, Response<Notifi_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Notifi_Model> call, Throwable t) {
                Log.e("themnotifi",t.getMessage());
            }
        });
        return data;
    }
}
