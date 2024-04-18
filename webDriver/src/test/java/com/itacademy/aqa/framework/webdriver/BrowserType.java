package com.itacademy.aqa.framework.webdriver;

public enum BrowserType {
    CHROME("web"),
    FIREFOX("web"),
    CHROME_EMULATOR("mobile"),
    EDGE("web"),
    REMOTE_CHROME("web");

    BrowserType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private String type;
}
