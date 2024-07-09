package com.example.uniman.Repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uniman.Model.Majors_Model;
import com.example.uniman.Model.NotiResponse;
import com.example.uniman.Model.NotisenData;
import com.example.uniman.Retrofit.APINotifcation;
import com.example.uniman.Retrofit.RetrofitNotification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Noti_Repository {
   private APINotifcation apiNotifcation;

   public Noti_Repository( ) {
      this.apiNotifcation = RetrofitNotification.getRetrofitNoti().create(APINotifcation.class);
   }
   public MutableLiveData<NotiResponse> notisenDataMutableLiveData(NotisenData notisenData) {
      MutableLiveData<NotiResponse> data = new MutableLiveData<>();
      apiNotifcation.senNotification(notisenData).enqueue(new Callback<NotiResponse>() {
         @Override
         public void onResponse(Call<NotiResponse> call, Response<NotiResponse> response) {
            if (response.isSuccessful()) {
               data.setValue(response.body());
            } else {
               Log.e("Noti_Repository", "Request failed: " + response.message());
            }
         }

         @Override
         public void onFailure(Call<NotiResponse> call, Throwable t) {
            Log.e("Noti_Repository", "Request failed: " + t.getMessage());
         }
      });
      return data;
   }
}
