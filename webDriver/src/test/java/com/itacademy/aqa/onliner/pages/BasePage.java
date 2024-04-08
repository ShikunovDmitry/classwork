package com.itacademy.aqa.onliner.pages;

import com.itacademy.aqa.onliner.elements.TopMenu;
import org.openqa.selenium.WebDriver;

public abstract class BasePage implements Page {
    protected static final int DEFAULT_PAGE_WAIT_IN_SECOND = 30;
    protected TopMenu topMenu;
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        this.topMenu = new TopMenu(webDriver);
    }

}
