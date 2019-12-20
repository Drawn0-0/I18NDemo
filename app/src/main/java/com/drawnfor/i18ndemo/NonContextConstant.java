package com.drawnfor.i18ndemo;

import com.drawnfor.i18nlibrary.I18NParam;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

public class NonContextConstant {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Language.Auto, Language.Chinese_Simple, Language.Korean, Language.English})
    public @interface Language {
        int Auto = 0;
        int Chinese_Simple = 1;
        int Korean = 2;
        int English = 3;
    }

    public static I18NParam i18NParam = new I18NParam("I18nPreference","I18nKey");

    public static @Language int DEFAULT_LANGUAGE = Language.Auto;
}
