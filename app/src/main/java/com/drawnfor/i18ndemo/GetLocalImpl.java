package com.drawnfor.i18ndemo;

import android.content.Context;
import com.drawnfor.i18nlibrary.GetLocal;
import com.drawnfor.i18nlibrary.I18NUtil;

import java.util.Locale;

public class GetLocalImpl implements GetLocal {
    private static GetLocalImpl instance;

    private GetLocalImpl() {
    }

    public static GetLocalImpl getInstance() {
        if (instance == null) {
            instance = new GetLocalImpl();
        }
        return instance;
    }


    @Override
    public Locale getLocal(Context context) {
        Locale locale;

        switch (I18NSpUtil.getLanguage(context, NonContextConstant.DEFAULT_LANGUAGE)) {
            case NonContextConstant.Language.English:
                locale = Locale.ENGLISH;
                break;
            case NonContextConstant.Language.Korean:
                locale = Locale.KOREA;
                break;
            case NonContextConstant.Language.Chinese_Simple:
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case NonContextConstant.Language.Auto:
            default:
                locale = I18NUtil.getInstance().getSysLocal();
                break;
        }

        return locale;
    }
}
