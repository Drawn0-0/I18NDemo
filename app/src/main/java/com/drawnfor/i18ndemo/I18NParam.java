package com.drawnfor.i18ndemo;

/**
 * 国际化的Sp参数
 */
public class I18NParam {
    private final String mSpFilename;
    private final String SpKey;

    public I18NParam(String spFilename, String spKey) {
        this.mSpFilename = spFilename;
        SpKey = spKey;
    }

    String getSpFilename() {
        return mSpFilename;
    }

    String getSpKey() {
        return SpKey;
    }
}
