package com.project.swapnil.weather.dashboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.swapnil.weather.R;
import com.project.swapnil.weather.model.WeatherResponse;
import com.project.swapnil.weather.util.DateUtil;
import com.project.swapnil.weather.util.SharedPrefUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.ContributesAndroidInjector;


public class DashboardActivity extends AppCompatActivity implements IDashboardView {

    @BindView(R.id.dashboard_location)
    TextView location;

    @BindView(R.id.dashboard_inputSearch)
    EditText inputSearch;

    @BindView(R.id.dashboard_temperature)
    TextView temperature;

    @BindView(R.id.dashboard_min_temperature)
    TextView minTemperature;

    @BindView(R.id.dashboard_max_temperature)
    TextView maxTemperature;

    @BindView(R.id.dashboard_date)
    TextView updateDate;

    @BindView(R.id.dashboard_weather_main)
    TextView weatherMain;

    @BindView(R.id.dashboard_weather_description)
    TextView weatherDescription;

    @BindView(R.id.dashboard_weather_icon)
    ImageView weatherIcon;

    @BindView(R.id.dashboard_progress_bar)
    ContentLoadingProgressBar progressBar;

    private DashboardPresenter mPresenter;
    private @StringRes int sharedLocationKey= R.string.shared_pref_location_key;
    private String tempUnit = "\u2109";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        mPresenter = new DashboardPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onDetach();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttach(this, SharedPrefUtil.getValue(this,sharedLocationKey));
    }

    @OnClick(R.id.dashboard_search_button)
    void onDashboardClicked() {
        String location = inputSearch.getText().toString();
        if(mPresenter != null) {
            mPresenter.onSearchButtonClicked(location);
        }
        hideSoftKeyboard();
    }

    @Override
    public void updateWeather(WeatherResponse weatherResponse) {
        updateSharedPrefValue(weatherResponse.getCityName());
        location.setText(weatherResponse.getCityName());
        if(weatherResponse.getTemperatureComponents() != null ) {
            String minTempPrefix = "Min : ";
            String maxTempPrefix = "Max : ";
            minTemperature.setText(minTempPrefix + weatherResponse.getTemperatureComponents().getMinTemperature() + tempUnit);
            maxTemperature.setText(maxTempPrefix + weatherResponse.getTemperatureComponents().getMaxTemperature() + tempUnit);
            temperature.setText(weatherResponse.getTemperatureComponents().getTemperature() + tempUnit);
        }
        if(weatherResponse.getWeather() != null) {
            weatherMain.setText(weatherResponse.getWeather().getmMain());
            weatherDescription.setText(weatherResponse.getWeather().getmDescription());
        }

        //update Time
        updateDate.setText(getString(R.string.last_updated_at) +
                DateUtil.getDateFromUTCTime(weatherResponse.getTime()));
    }

    private void updateSharedPrefValue(String cityName) {
        SharedPrefUtil.writeValue(this, sharedLocationKey, cityName);
    }

    @Override
    public void showLoading() {
        progressBar.show();
    }

    @Override
    public void hideLoading() {
        progressBar.hide();
    }

    @Override
    public void setWeatherIcon(String url) {
        Glide.with(this)
                .load(url)
                .into(weatherIcon);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
