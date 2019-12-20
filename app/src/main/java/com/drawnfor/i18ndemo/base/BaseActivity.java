package com.drawnfor.i18ndemo.base;

import android.content.Context;

import com.drawnfor.i18ndemo.GetLocalImpl;
import com.drawnfor.i18nlibrary.I18NUtil;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(I18NUtil.updateBaseContext(newBase, GetLocalImpl.getInstance()));
    }

}
