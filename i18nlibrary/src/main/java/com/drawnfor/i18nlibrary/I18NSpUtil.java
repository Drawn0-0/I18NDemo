package com.drawnfor.i18nlibrary;

import android.content.Context;
import android.content.SharedPreferences;

public class I18NSpUtil {


    private I18NSpUtil() {
    }

    public static void setLanguage(Context context, I18NParam spParam, int languageOptional) {
        SharedPreferences.Editor edit = context.getSharedPreferences(spParam.getSpFilename(), Context.MODE_PRIVATE).edit();
        edit.putInt(spParam.getSpKey(), languageOptional);
        edit.apply();
    }

    public static int getLanguage(Context context, I18NParam spParam, int defaultLanguage) {
        return context.getSharedPreferences(spParam.getSpFilename(), Context.MODE_PRIVATE).getInt(spParam.getSpKey(), defaultLanguage);
    }


}
