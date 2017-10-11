package com.project.swapnil.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Swapnil on 10-Oct-17.
 */

public class TemperatureComponents {

    @SerializedName("temp")
    private float temperature;

    @SerializedName("pressure")
    private float pressure;

    @SerializedName("humidity")
    private float humidity;

    @SerializedName("temp_min")
    private float minTemperature;

    @SerializedName("temp_max")
    private float maxTemperature;

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }
}
