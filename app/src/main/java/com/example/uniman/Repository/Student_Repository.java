package com.example.uniman.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Student_Model;
import com.example.uniman.Model.Uploadingavata_Model;
import com.example.uniman.Retrofit.API;
import com.example.uniman.Retrofit.Retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Student_Repository {

    private API api;

    public Student_Repository() {
        this.api = Retrofit.getRetrofit().create(API.class);
    }

    public MutableLiveData<Student_Model> student_modelMutableLiveData(String ma){
        MutableLiveData<Student_Model> data = new MutableLiveData<>();
        api.student(ma).enqueue(new Callback<Student_Model>() {
            @Override
            public void onResponse(Call<Student_Model> call, Response<Student_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Student_Model> call, Throwable t) {
                Log.e("student",t.getMessage());
            }
        });
        return data;
    }

    public MutableLiveData<Student_Model> studentlist_modelMutableLiveData(String lop){
        MutableLiveData<Student_Model> data = new MutableLiveData<>();
        api.studentlist(lop).enqueue(new Callback<Student_Model>() {
            @Override
            public void onResponse(Call<Student_Model> call, Response<Student_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Student_Model> call, Throwable t) {
                Log.e("studentlist",t.getMessage());
            }
        });
        return data;
    }

    // ảnh
   public MutableLiveData<Uploadingavata_Model> uploadingavata_modelMutableLiveData (MultipartBody.Part file){
        MutableLiveData<Uploadingavata_Model> data = new MutableLiveData<>();
        api.uploadimg(file).enqueue(new Callback<Uploadingavata_Model>() {
            @Override
            public void onResponse(Call<Uploadingavata_Model> call, Response<Uploadingavata_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Uploadingavata_Model> call, Throwable t) {
                Log.e("uploadimg",t.getMessage());
            }
        });
        return data;
   }

    public MutableLiveData<Uploadingavata_Model> updateavata_modelMutableLiveData (String ma,String hinhanh){
        MutableLiveData<Uploadingavata_Model> data = new MutableLiveData<>();
        api.updateavata(ma,hinhanh).enqueue(new Callback<Uploadingavata_Model>() {
            @Override
            public void onResponse(Call<Uploadingavata_Model> call, Response<Uploadingavata_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Uploadingavata_Model> call, Throwable t) {
                Log.e("updateavata",t.getMessage());
            }
        });
        return data;
    }

    // xoa sinh viên
    public MutableLiveData<Uploadingavata_Model> delete_modelMutableLiveData(int id){
        MutableLiveData<Uploadingavata_Model> data = new MutableLiveData<>();
        api.delete(id).enqueue(new Callback<Uploadingavata_Model>() {
            @Override
            public void onResponse(Call<Uploadingavata_Model> call, Response<Uploadingavata_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Uploadingavata_Model> call, Throwable t) {
                Log.e("studentlist",t.getMessage());
            }
        });
        return data;
    }
    // update sinh viên
    public MutableLiveData<Uploadingavata_Model> update_modelMutableLiveData(int id,String ma, String name, int gioitinh, String lop, String nganh,
                                                                             String chuyennganh, String hinhanh){
        MutableLiveData<Uploadingavata_Model> data = new MutableLiveData<>();
        api.updatesv(id,ma, name, gioitinh, lop, nganh, chuyennganh, hinhanh).enqueue(new Callback<Uploadingavata_Model>() {
            @Override
            public void onResponse(Call<Uploadingavata_Model> call, Response<Uploadingavata_Model> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Uploadingavata_Model> call, Throwable t) {
                Log.e("updatesv",t.getMessage());
            }
        });
        return data;
    }
}
