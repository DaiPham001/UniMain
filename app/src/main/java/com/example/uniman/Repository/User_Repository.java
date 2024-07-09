package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.User_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Repository {
    private API api;

    public User_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    public MutableLiveData<User_Model> user_modelMutableLiveData(String ma,String password){
        MutableLiveData<User_Model> data = new MutableLiveData<>();
        api.login(ma,password).enqueue(new Callback<User_Model>() {
            @Override
            public void onResponse(Call<User_Model> call, Response<User_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User_Model> call, Throwable t) {
                Log.e("login",t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<User_Model> changeMutableLiveData(String ma,String password){
        MutableLiveData<User_Model> data = new MutableLiveData<>();
        api.change(ma,password).enqueue(new Callback<User_Model>() {
            @Override
            public void onResponse(Call<User_Model> call, Response<User_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User_Model> call, Throwable t) {
                Log.e("login",t.getMessage());
            }
        });
        return data;
    }

    // cap tai khoan
    public MutableLiveData<User_Model> registter_modelMutableLiveData(String ma,String password,int role,String uid){
        MutableLiveData<User_Model> data = new MutableLiveData<>();
        api.registter(ma,password,role,uid).enqueue(new Callback<User_Model>() {
            @Override
            public void onResponse(Call<User_Model> call, Response<User_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User_Model> call, Throwable t) {
                Log.e("registter",t.getMessage());
            }
        });
        return data;
    }

    // them token
    public MutableLiveData<User_Model> themtoken_modelMutableLiveData(int id,String token){
        MutableLiveData<User_Model> data = new MutableLiveData<>();
        api.themtoken(id,token).enqueue(new Callback<User_Model>() {
            @Override
            public void onResponse(Call<User_Model> call, Response<User_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User_Model> call, Throwable t) {
                Log.e("themtoken",t.getMessage());
            }
        });
        return data;
    }
}
