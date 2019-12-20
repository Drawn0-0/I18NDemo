package com.drawnfor.i18ndemo.bean;

public class LanguageBean {
    private int code;
    private String name;

    public LanguageBean(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LanguageBean) {
            return ((LanguageBean) obj).code == this.code && ((LanguageBean) obj).name.equals(this.name);
        }
        return false;
    }
}
