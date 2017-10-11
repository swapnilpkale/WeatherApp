package com.project.swapnil.weather.dashboard;

public interface IDashboardPresenter <V extends IDashboardView> {
    void onAttach(V dashboardView,  String defaultLocation);
    void onSearchButtonClicked(String location);
    void onDetach();
}
