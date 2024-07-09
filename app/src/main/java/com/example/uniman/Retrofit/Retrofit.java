package com.example.uniman.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static retrofit2.Retrofit retrofit;

    public static retrofit2.Retrofit getRetrofit() {
        if (retrofit == null) {
        retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://192.168.58.244:7070/unimain/")
                .addConverterFactory(GsonConverterFactory
                        .create())
                .build();

    }
//        if (retrofit == null) {
//            retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://192.168.155.43:7070/unimain/")
//                    .addConverterFactory(GsonConverterFactory
//                            .create())
//                    .build();
//
//        }
        return retrofit;
    }
}
