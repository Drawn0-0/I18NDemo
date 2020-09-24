package com.drawnfor.i18ndemo;

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

    public static @Language int DEFAULT_LANGUAGE = Language.Auto;
}
