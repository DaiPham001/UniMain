package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Results_Model;
import com.example.uniman.Model.Retest_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retest_Repository {
    private API api;

    public Retest_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    //
    public MutableLiveData<Retest_Model> getthilai_modelMutableLiveData(String msv){
        MutableLiveData<Retest_Model> data = new MutableLiveData<>();
        api.getthilai(msv).enqueue(new Callback<Retest_Model>() {
            @Override
            public void onResponse(Call<Retest_Model> call, Response<Retest_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Retest_Model> call, Throwable t) {
                Log.e("getthilai",t.getMessage());
            }
        });
        return data;
    }

    //
    public MutableLiveData<Retest_Model> getthicaithien_modelMutableLiveData(String msv){
        MutableLiveData<Retest_Model> data = new MutableLiveData<>();
        api.getthicaithien(msv).enqueue(new Callback<Retest_Model>() {
            @Override
            public void onResponse(Call<Retest_Model> call, Response<Retest_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Retest_Model> call, Throwable t) {
                Log.e("getthicaithien",t.getMessage());
            }
        });
        return data;
    }

    // thêm môn thi lại gv
    public MutableLiveData<Retest_Model> themmonthilai_modelMutableLiveData(String msv,String mamh,String lophp,String tenmh, int tc,String diemck1,String ghichu ){
        MutableLiveData<Retest_Model> data = new MutableLiveData<>();
        api.themthilai(msv,mamh,lophp,tenmh,tc,diemck1,ghichu).enqueue(new Callback<Retest_Model>() {
            @Override
            public void onResponse(Call<Retest_Model> call, Response<Retest_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Retest_Model> call, Throwable t) {
                Log.e("themthilai",t.getMessage());
            }
        });
        return data;
    }

    // thêm môn thi lại sv
    public MutableLiveData<Retest_Model> themmonthilaisv_modelMutableLiveData(String msv,String lophp,String tenmh, int tc,float tienthi,String ngaydangky ){
        MutableLiveData<Retest_Model> data = new MutableLiveData<>();
        api.themthilaisv(msv,lophp,tenmh,tc,tienthi,ngaydangky).enqueue(new Callback<Retest_Model>() {
            @Override
            public void onResponse(Call<Retest_Model> call, Response<Retest_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Retest_Model> call, Throwable t) {
                Log.e("themthilaisv",t.getMessage());
            }
        });
        return data;
    }

    // get môn thi lại
    public MutableLiveData<Retest_Model> getmonthilai_modelMutableLiveData(String msv){
        MutableLiveData<Retest_Model> data = new MutableLiveData<>();
        api.getmonthilai(msv).enqueue(new Callback<Retest_Model>() {
            @Override
            public void onResponse(Call<Retest_Model> call, Response<Retest_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Retest_Model> call, Throwable t) {
                Log.e("getmonthilai",t.getMessage());
            }
        });
        return data;
    }
}
