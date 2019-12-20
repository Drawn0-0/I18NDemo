package com.drawnfor.i18ndemo.base;

import android.app.Service;
import android.content.Context;

import com.drawnfor.i18ndemo.GetLocalImpl;
import com.drawnfor.i18nlibrary.I18NUtil;

public abstract class BaseService extends Service {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(I18NUtil.updateBaseContext(base, GetLocalImpl.getInstance()));
    }
}
