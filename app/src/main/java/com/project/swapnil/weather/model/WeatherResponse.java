package com.project.swapnil.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Swapnil on 10-Oct-17.
 */

public class WeatherResponse {

    @SerializedName("weather")
    Weather[] weather;

    @SerializedName("main")
    TemperatureComponents temperatureComponents;

    @SerializedName("name")
    String cityName;

    @SerializedName("dt")
    long time;

    public Weather getWeather() {
        if(weather.length > 0) {
            return weather[0];
        }
        return null;
    }

    public TemperatureComponents getTemperatureComponents() {
        return temperatureComponents;
    }

    public String getCityName() {
        return cityName;
    }

    public long getTime() {
        return time;
    }
}
