package com.example.uniman.Retrofit;

import android.database.Observable;

import com.example.uniman.Model.NotiResponse;
import com.example.uniman.Model.NotisenData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APINotifcation {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAGXLrJ88:APA91bH0hNjS4p0Qo7VaQF_oKAYc9Vg5Cm_MSj4AQa_7KlC29ewtyavUWU8tspenG4IWUn9cL-qBIrS60CMH1T59xw43LHow0kBavK7LrVuperNn2KQHi3N8PJQIwWc3JlhwIMkI_k4G" // Thay YOUR_SERVER_KEY bằng khóa máy chủ của bạn từ Firebase Console
    })
    @POST("fcm/send")
    Call<NotiResponse> senNotification(@Body NotisenData data);
}
