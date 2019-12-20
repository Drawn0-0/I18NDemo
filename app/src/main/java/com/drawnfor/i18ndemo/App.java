package com.drawnfor.i18ndemo;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.drawnfor.i18nlibrary.I18NUtil;

import androidx.annotation.NonNull;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(I18NUtil.updateApplicationBaseContext(base, GetLocalImpl.getInstance()));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        I18NUtil.getInstance().onConfigurationChanged(newConfig);
    }
}
