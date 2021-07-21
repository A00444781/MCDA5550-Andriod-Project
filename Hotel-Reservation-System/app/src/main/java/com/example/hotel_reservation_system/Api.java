package com.example.hotel_reservation_system;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient() {

        RestAdapter adapter = new RestAdapter.Builder().
                setEndpoint("https://hotelreservationapi.azurewebsites.net/").
                build();

        ApiInterface api = adapter.create(ApiInterface.class);
        return api;

    }
}
