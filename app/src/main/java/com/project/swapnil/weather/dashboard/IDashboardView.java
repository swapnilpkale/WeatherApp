package com.project.swapnil.weather.dashboard;

import android.graphics.drawable.Drawable;

import com.project.swapnil.weather.model.WeatherResponse;

/**
 * Created by Swapnil on 10-Oct-17.
 */

public interface IDashboardView {
    void updateWeather(WeatherResponse weatherResponse);
    void showLoading();
    void hideLoading();
    void setWeatherIcon(String uri);
    void showError();
}
