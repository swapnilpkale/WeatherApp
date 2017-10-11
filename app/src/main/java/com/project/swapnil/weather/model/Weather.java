package com.project.swapnil.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Swapnil on 10-Oct-17.
 */

public class Weather {

    @SerializedName("id")
    private String mId;

    @SerializedName("main")
    private String mMain;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("icon")
    private String mIcon;

    public String getmId() {
        return mId;
    }

    public String getmMain() {
        return mMain;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmIcon() {
        return mIcon;
    }
}
