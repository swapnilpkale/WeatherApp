package com.project.swapnil.weather.dashboard;

import android.content.Context;
import android.support.annotation.StringRes;

import com.project.swapnil.weather.BuildConfig;
import com.project.swapnil.weather.R;
import com.project.swapnil.weather.model.WeatherResponse;
import com.project.swapnil.weather.rest.ApiClient;
import com.project.swapnil.weather.rest.ApiInterface;
import com.project.swapnil.weather.rest.ApiServiceManager;
import com.project.swapnil.weather.util.SharedPrefUtil;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Swapnil on 10-Oct-17.
 */

public class DashboardPresenter implements IDashboardPresenter {

    private IDashboardView mView;
    private ApiServiceManager apiServiceManager;

    public DashboardPresenter() {
        apiServiceManager = new ApiServiceManager();
    }

    @Override
    public void onAttach(IDashboardView dashboardView, String defaultLocation) {
        mView = dashboardView;
        fetchWeather(defaultLocation);
    }

    @Override
    public void onSearchButtonClicked(String location) {
        fetchWeather(location);
    }

    @Override
    public void onDetach() {
    }

    public void fetchWeather(String location) {
        Call<WeatherResponse> call = apiServiceManager.getWeatherForCity(location);
        mView.showLoading();

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse>call, Response<WeatherResponse> response) {
                if(response != null && response.isSuccessful()) {
                    updateView(response.body());
                }
                mView.hideLoading();
            }

            @Override
            public void onFailure(Call<WeatherResponse>call, Throwable t) {
                // Log error here since request failed
                mView.showError();
                mView.hideLoading();
            }
        });
    }

    private void updateView(WeatherResponse weatherResponse){
        if(weatherResponse != null) {
            mView.updateWeather(weatherResponse);
            mView.setWeatherIcon(genrateImageResourceUrl(weatherResponse.getWeather().getmIcon()));
        }
    }

    private String genrateImageResourceUrl(String image) {
        return String.format("%s%s.png",BuildConfig.IMAGE_RESOUCE_BASE_URL , image);
    }
}
