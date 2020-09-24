package com.drawnfor.i18ndemo.activity;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.drawnfor.i18ndemo.NonContextConstant;
import com.drawnfor.i18ndemo.R;
import com.drawnfor.i18ndemo.adapter.SingleAdapter;
import com.drawnfor.i18ndemo.base.BaseActivity;
import com.drawnfor.i18ndemo.bean.LanguageBean;
import com.drawnfor.i18ndemo.service.NotifyService;
import com.drawnfor.i18ndemo.I18NSpUtil;
import com.drawnfor.i18nlibrary.I18NUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView rvList;
    private SingleAdapter adapter;
    private boolean bindResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        List<LanguageBean> listData = new ArrayList<>();
        listData.add(new LanguageBean(NonContextConstant.Language.Auto, getString(R.string.auto)));
        listData.add(new LanguageBean(NonContextConstant.Language.Chinese_Simple, getString(R.string.chinese_simple)));
        listData.add(new LanguageBean(NonContextConstant.Language.Korean, getString(R.string.korean)));
        listData.add(new LanguageBean(NonContextConstant.Language.English, getString(R.string.english)));

        adapter = new SingleAdapter(this,listData,getDefaultLanguageBean());
        rvList.setAdapter(adapter);
    }

    private LanguageBean getDefaultLanguageBean() {
        int languageCode = I18NSpUtil.getLanguage(this, NonContextConstant.DEFAULT_LANGUAGE);
        switch (languageCode) {
            case NonContextConstant.Language.Chinese_Simple:
                return new LanguageBean(languageCode, getString(R.string.chinese_simple));
            case NonContextConstant.Language.Korean:
                return new LanguageBean(languageCode, getString(R.string.korean));
            case NonContextConstant.Language.English:
                return new LanguageBean(languageCode, getString(R.string.english));
            case NonContextConstant.Language.Auto:
            default:
                return new LanguageBean(languageCode, getString(R.string.auto));
        }
    }

    private void initView() {
        rvList = findViewById(R.id.main_rv_list);
        Button btnApply = findViewById(R.id.main_btn_apply);
        Button btnService = findViewById(R.id.main_btn_service);

        btnApply.setOnClickListener(this);
        btnService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_apply:
                LanguageBean selectBean = adapter.getSelectBean();
                if (selectBean!=null){
                    I18NSpUtil.setLanguage(this, selectBean.getCode());
                    restartApp();
                }
                break;
            case R.id.main_btn_service:
                bindResult = bindService(new Intent(this, NotifyService.class), connection, BIND_AUTO_CREATE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bindResult){
            unbindService(connection);
        }
    }

    private void restartApp() {
        I18NUtil.finishAllActivity(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NotifyService.MyBinder binder = (NotifyService.MyBinder) service;
            binder.getService().sendNotify();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
