package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Majors;
import com.example.uniman.Model.Majors_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Majors_Repository {
    private API api;

    public Majors_Repository() {
        this.api = Retrofit.getRetrofit().create(API .class);
    }

    public MutableLiveData<Majors_Model> gettennganh_modelMutableLiveData(){
        MutableLiveData<Majors_Model> data = new MutableLiveData<>();
        api.gettennganh().enqueue(new Callback<Majors_Model>() {
            @Override
            public void onResponse(Call<Majors_Model> call, Response<Majors_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Majors_Model> call, Throwable t) {
                Log.e("gettennganh",t.getMessage());
            }
        });
        return data;
    }

    // thêm nganh
    public MutableLiveData<Majors_Model> themnganh_modelMutableLiveData(String manganh,String tennganh,String chuyennganh){
        MutableLiveData<Majors_Model> data = new MutableLiveData<>();
        api.themnganh(manganh,tennganh,chuyennganh).enqueue(new Callback<Majors_Model>() {
            @Override
            public void onResponse(Call<Majors_Model> call, Response<Majors_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Majors_Model> call, Throwable t) {
                Log.e("themnganh",t.getMessage());
            }
        });
        return data;
    }

    // update nganh
    public MutableLiveData<Majors_Model> update_modelMutableLiveData(int id,String manganh,String tennganh){
        MutableLiveData<Majors_Model> data = new MutableLiveData<>();
        api.updatenganh(id,manganh,tennganh).enqueue(new Callback<Majors_Model>() {
            @Override
            public void onResponse(Call<Majors_Model> call, Response<Majors_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Majors_Model> call, Throwable t) {
                Log.e("update",t.getMessage());
            }
        });
        return data;
    }
}

