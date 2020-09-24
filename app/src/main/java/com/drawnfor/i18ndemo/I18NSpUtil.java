package com.drawnfor.i18ndemo;

import android.content.Context;
import android.content.SharedPreferences;

public class I18NSpUtil {
    private static final String SP_FILE_NAME = "I18nPreference";
    private static final String LANGUAGE_KEY = "I18nKey";


    private I18NSpUtil() {
    }

    public static void setLanguage(Context context, int languageOptional) {
        SharedPreferences.Editor edit = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).edit();
        edit.putInt(LANGUAGE_KEY, languageOptional);
        edit.apply();
    }

    public static int getLanguage(Context context, int defaultLanguage) {
        return context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE).getInt(LANGUAGE_KEY, defaultLanguage);
    }


}
