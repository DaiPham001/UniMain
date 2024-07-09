package com.example.uniman.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNotification {
    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getRetrofitNoti() {
        if (retrofit == null) {
        retrofit = new retrofit2.Retrofit.Builder().baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();

    }

        return retrofit;
    }
}
