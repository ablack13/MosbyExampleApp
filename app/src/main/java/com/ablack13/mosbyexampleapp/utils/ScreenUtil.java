package com.ablack13.mosbyexampleapp.utils;

import android.content.Context;

import com.ablack13.mosbyexampleapp.R;

/**
 * Created by ablack13 on 17.12.16.
 */

public class ScreenUtil {
    public static boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.isTablet);
    }

    public static boolean isLargeScreen(Context context) {
        return context.getResources().getBoolean(R.bool.isLargeScreen);
    }

}
