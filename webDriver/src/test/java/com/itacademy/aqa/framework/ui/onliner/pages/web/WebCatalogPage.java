package com.itacademy.aqa.framework.ui.onliner.pages.web;

import com.itacademy.aqa.framework.ui.onliner.TopMenuEnum;
import com.itacademy.aqa.framework.ui.onliner.pages.BasePage;
import com.itacademy.aqa.framework.ui.onliner.pages.ICatalogPage;
import com.itacademy.aqa.framework.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class WebCatalogPage extends BasePage implements ICatalogPage {

    private static final By CATALOG_PAGE_LOCATOR = By.xpath("//*[contains(@class,'catalog-navigation__title')][contains(text(),'Каталог')]");
    private static final TopMenuEnum MENU_ELEMENT = TopMenuEnum.CATALOG;

    public WebCatalogPage(WebDriver webDriver) {
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

    @Override
    public List<String> getMenuList() {
        return List.of();
    }
}
