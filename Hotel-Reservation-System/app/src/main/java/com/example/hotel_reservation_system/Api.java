package com.example.hotel_reservation_system;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static ApiInterface getClient() {

//        RestAdapter adapter = new RestAdapter.Builder().
//                setEndpoint("https://hotelreservationapi.azurewebsites.net").
//                build();
//
//        ApiInterface api = adapter.create(ApiInterface.class);
//        return api;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hotelreservationapi.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface api = retrofit.create(ApiInterface.class);
        return api;

    }
}
