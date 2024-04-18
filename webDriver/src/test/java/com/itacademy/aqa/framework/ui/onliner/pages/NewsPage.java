package com.itacademy.aqa.framework.ui.onliner.pages;

import com.itacademy.aqa.framework.ui.onliner.TopMenuEnum;
import org.openqa.selenium.WebDriver;

public class NewsPage extends BasePage implements Page {
    private static final TopMenuEnum MENU_ELEMENT = TopMenuEnum.NEWS;
    public NewsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void openPage() {
        topMenu.clickOnItem(MENU_ELEMENT);
    }

    @Override
    public boolean isOpened() {
        return false;
    }
}
