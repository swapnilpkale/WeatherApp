package com.project.swapnil.weather.rest;

/**
 * Created by Swapnil on 10-Oct-17.
 */

import com.project.swapnil.weather.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("weather/")
    Call<WeatherResponse> getWeather(@Query("q") String locationQuery, @Query("APPID") String appId,@Query("units") String units);

}