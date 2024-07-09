package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Results_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class Results_Repository {

    private API api;

    public Results_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    /// theo role 1
    public MutableLiveData<Results_Model> results_modelMutableLiveData(String msv){
        MutableLiveData<Results_Model> data = new MutableLiveData<>();
        api.getresults(msv).enqueue(new Callback<Results_Model>() {
            @Override
            public void onResponse(Call<Results_Model> call, Response<Results_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Results_Model> call, Throwable t) {
                Log.e("results",t.getMessage());
            }
        });
        return data;
    }

    /// theo role 2
    public MutableLiveData<Results_Model> hienthikq_modelMutableLiveData(String msv,String lop){
        MutableLiveData<Results_Model> data = new MutableLiveData<>();
        api.hienthikq(msv,lop).enqueue(new Callback<Results_Model>() {
            @Override
            public void onResponse(Call<Results_Model> call, Response<Results_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Results_Model> call, Throwable t) {
                Log.e("hienthikq",t.getMessage());
            }
        });
        return data;
    }

    /// theo role 2
    // cập nhật điểm
    public MutableLiveData<Results_Model> nhapkq_modelMutableLiveData(String msv, String mamh, float diemcc, float diemhs1, float diemhs2, float diemhs3,int img,
                                                                      float diemck1,float diemthi, float tongdiem, float tb4, String diemchu, String xeploai,String ghichu){
        MutableLiveData<Results_Model> data = new MutableLiveData<>();
        api.nhapkq(msv,mamh,diemcc,diemhs1,diemhs2,diemhs3,img,diemck1,diemthi,tongdiem,tb4,diemchu,xeploai,ghichu).enqueue(new Callback<Results_Model>() {
            @Override
            public void onResponse(Call<Results_Model> call, Response<Results_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Results_Model> call, Throwable t) {
                Log.e("nhapkq",t.getMessage());
            }
        });
        return data;
    }
}
