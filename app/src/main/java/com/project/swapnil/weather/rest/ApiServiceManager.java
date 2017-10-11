package com.project.swapnil.weather.rest;

import com.project.swapnil.weather.BuildConfig;
import com.project.swapnil.weather.model.WeatherResponse;

import retrofit2.Call;

/**
 * Created by Swapnil on 11-Oct-17.
 */

public class ApiServiceManager {

    public static String API_KEY = BuildConfig.API_KEY;
    public static String localeUnit = "imperial";

    public Call<WeatherResponse> getWeatherForCity(String city) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        return apiService.getWeather(generateCityWithCountryCode(city), API_KEY , getLocaleUnit());
    }

    private String generateCityWithCountryCode(String city) {
        return city +",us";
    }

    private String getLocaleUnit() {
        return localeUnit;
    }
}
