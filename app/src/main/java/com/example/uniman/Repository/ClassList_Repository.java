package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Class_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassList_Repository {
    private API api;

    public ClassList_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    public MutableLiveData<Class_Model> class_modelMutableLiveData(String manganh){
        MutableLiveData<Class_Model> data = new MutableLiveData<>();
        api.classlist(manganh).enqueue(new Callback<Class_Model>() {
            @Override
            public void onResponse(Call<Class_Model> call, Response<Class_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Class_Model> call, Throwable t) {
                Log.e("classlist",t.getMessage());
            }
        });
        return data;
    }

    // lấy tên lớp spiner sửa
    public MutableLiveData<Class_Model> getclass_modelMutableLiveData(String manganh){
        MutableLiveData<Class_Model> data = new MutableLiveData<>();
        api.getclass(manganh).enqueue(new Callback<Class_Model>() {
            @Override
            public void onResponse(Call<Class_Model> call, Response<Class_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Class_Model> call, Throwable t) {
                Log.e("classlist",t.getMessage());
            }
        });
        return data;
    }
}
