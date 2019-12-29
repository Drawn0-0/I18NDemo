# I18NLibrary
an Android library for international.  

## 使用
* 实现 GetLocal 接口，用于获取sp文件中保存的Local值。[GetLocalImpl](https://github.com/Drawn0-0/I18NDemo/blob/master/app/src/main/java/com/drawnfor/i18ndemo/GetLocalImpl.java)
* 定义你需要用到的local值，设置I18N sp文件的参数。[Local 定义](https://github.com/Drawn0-0/I18NDemo/blob/master/app/src/main/java/com/drawnfor/i18ndemo/NonContextConstant.java)
* 在Application，BaseActivity和BaseService中调用相应方法
  ```
    //在Application中
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(I18NUtil.updateApplicationBaseContext(base, GetLocalImpl.getInstance()));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        I18NUtil.getInstance().onConfigurationChanged(newConfig);
    }
    
    //在BaseActivity 和 BaseService中
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(I18NUtil.updateBaseContext(newBase, GetLocalImpl.getInstance()));
    }
  
  ``
  
 修改语言时，调用I18NSpUtil.setLanguage()保存设置，然后重启App即可。  
 使用很简单，Demo里都写好了。

## 引用
```
implementation 'com.drawnfor:i18nlibrary:1.0.0'
```

## 注意
如果你依赖的是androidx appcompat:1.1.0版本，那么api21至api25的设备，语言的更改将会失败。这是系统的问题，最简单的做法将appcompat改为1.0.2或者将要发布的1.1.1版本。
