package com.project.swapnil.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Swapnil on 11-Oct-17.
 */

public class DateUtil {

    public static String getDateFromUTCTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy ',' h:mm a");
        return sdf.format(time * 1000L); //Unix time stamp is in seconds
    }

}
