package com.example.hotel_reservation_system;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface ApiInterface {

    @GET("/hotelList")
    public void getHotelLists(Callback<List<HotelListData>> callback);

//    @POST("reservation")
//    public void getConfirmationNumber(Callback<List<HotelListData>> callback);
}
