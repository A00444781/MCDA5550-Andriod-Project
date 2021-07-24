package com.example.hotel_reservation_system;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/hotelList")
    Call<List<HotelListData>> getHotelsList();

    @POST("/reservation")
    Call<Integer>getConfirmationId(@Body ReservationDetails reservationDetails);
}
