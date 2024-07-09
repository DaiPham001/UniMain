package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Majors_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Industry_Repository {
    private API api ;

    public Industry_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    public MutableLiveData<Majors_Model> majors_modelMutableLiveData(){
        MutableLiveData<Majors_Model> data = new MutableLiveData<>();
        api.getindustry().enqueue(new Callback<Majors_Model>() {
            @Override
            public void onResponse(Call<Majors_Model> call, Response<Majors_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Majors_Model> call, Throwable t) {
                Log.e("industry",t.getMessage());
            }
        });
        return data;
    }

    // spinner ngành
    public MutableLiveData<Majors_Model> chnganh_modelMutableLiveData(String manganh){
        MutableLiveData<Majors_Model> data = new MutableLiveData<>();
        api.getchnganh(manganh).enqueue(new Callback<Majors_Model>() {
            @Override
            public void onResponse(Call<Majors_Model> call, Response<Majors_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Majors_Model> call, Throwable t) {
                Log.e("industry",t.getMessage());
            }
        });
        return data;
    }
}
