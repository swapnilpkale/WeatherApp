package com.project.swapnil.weather.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import com.project.swapnil.weather.R;

/**
 * Created by Swapnil on 11-Oct-17.
 */

public class SharedPrefUtil {

    public static void writeValue(Activity context, @StringRes int key, String value) {
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(key), value);
        editor.commit();
    }
    public static String getValue(Activity context, @StringRes int key) {
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        String defaultValue = context.getResources().getString(R.string.default_location);
        return sharedPref.getString(context.getString(key), defaultValue);
    }
}
