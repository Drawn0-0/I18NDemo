package com.drawnfor.i18nlibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

/**
 * 国际化工具类
 * 使用方法，在Application的 attachBaseContext 直接super.attachBaseContext(I18NUtil.updateApplicationBaseContext(base, GetLocalImpl));
 * 在Application onConfigurationChanged 调用 I18NUtil.getInstance().onConfigurationChanged(newConfig);
 * 在Activity或者Service的 attachBaseContext 直接super.attachBaseContext(I18NUtil.updateBaseContext(newBase, GetLocalImpl));
 */
public class I18NUtil {
    private static I18NUtil instance;
    private static Locale mSysLocal;

    private I18NUtil() {
    }

    public static I18NUtil getInstance() {
        if (instance == null) {
            instance = new I18NUtil();
        }
        return instance;
    }

    public Locale getSysLocal() {
        if (mSysLocal == null) {
            mSysLocal = Locale.getDefault();
        }
        return mSysLocal;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        mSysLocal = getLocalFromConfig(newConfig);
    }

    public static Locale getLocalFromConfig(Configuration newConfig) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = newConfig.getLocales().get(0);
        } else {
            locale = newConfig.locale;
        }
        return locale;
    }

    public static void finishAllActivity(Activity context){
        context.finishAffinity();
    }

    public static Context updateApplicationBaseContext(Context context, GetLocal getLocal) {
        mSysLocal = Locale.getDefault();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createNewLocalContext(context, getLocal.getLocal(context));
        } else {
            updateContextLocal(context, getLocal.getLocal(context));
            return context;
        }
    }

    public static Context updateBaseContext(Context context, GetLocal getLocal) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createNewLocalContext(context, getLocal.getLocal(context));
        } else {
            updateContextLocal(context, getLocal.getLocal(context));
            return context;
        }
    }

    private static void updateContextLocal(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context createNewLocalContext(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

}
