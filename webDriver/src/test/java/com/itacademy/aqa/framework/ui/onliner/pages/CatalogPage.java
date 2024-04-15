package com.itacademy.aqa.framework.ui.onliner.pages;

import com.itacademy.aqa.framework.ui.onliner.TopMenuEnum;
import com.itacademy.aqa.framework.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    private static final By CATALOG_PAGE_LOCATOR = By.xpath("//*[contains(@class,'catalog-navigation__title')][contains(text(),'Каталог')]");
    private static final TopMenuEnum MENU_ELEMENT = TopMenuEnum.CATALOG;

    public CatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void openPage() {
        topMenu.clickOnItem(MENU_ELEMENT);
    }

    @Override
    public boolean isOpened() {
        WaitUtil.waitUntilElementVisible(webDriver, CATALOG_PAGE_LOCATOR, DEFAULT_PAGE_WAIT_IN_SECOND);
        return webDriver.findElements(CATALOG_PAGE_LOCATOR).size() > 0;
    }
}
