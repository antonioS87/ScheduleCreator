package com.example.schedulecreator.holidayApi;


import com.example.schedulecreator.holidayApi.HolidayResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HolidayApiInterface {


    @GET("api/v2/holidays")
    Call<HolidayResponse> getHolidays(@Query("api_key") String apiKey, @Query("country") String country, @Query("year") String year);

}