package com.example.schedulecreator.HolidayApi;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HolidayApiInterface {


    @GET("api/v2/holidays")
    Call<HolidayResponse> getHolidays(@Query("api_key") String apiKey, @Query("country") String country, @Query("year") String year, @Query("type") String type);

}