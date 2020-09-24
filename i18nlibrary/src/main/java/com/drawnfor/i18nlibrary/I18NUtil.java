package com.drawnfor.i18nlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
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
        mSysLocal = Locale.getDefault();
    }

    public static I18NUtil getInstance() {
        if (instance == null) {
            instance = new I18NUtil();
        }
        return instance;
    }

    public Locale getSysLocal() {
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
        return updateContext(context,getLocal.getLocal(context));
    }

    public static Context updateBaseContext(Context context, GetLocal getLocal) {
        return updateContext(context,getLocal.getLocal(context));
    }

    private static Context updateContext(Context context, Locale locale){
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        Locale.setDefault(locale);
        return context.createConfigurationContext(configuration);
    }

}
