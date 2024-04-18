package com.itacademy.aqa.framework.ui.onliner.pages.mobile;

import com.itacademy.aqa.framework.ui.onliner.TopMenuEnum;
import com.itacademy.aqa.framework.ui.onliner.pages.BasePage;
import com.itacademy.aqa.framework.ui.onliner.pages.ICatalogPage;
import com.itacademy.aqa.framework.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class MobileCatalogPage extends BasePage implements ICatalogPage {

    private static final By CATALOG_PAGE_LOCATOR = By.xpath("//*[contains(@class,'b-main-navigation')][contains(text(),'Каталог')]");
    private static final By MENU_ITEMS_LOCATOR = By.xpath("//ul[contains(@class,'catalog-navigation-classifier')]/li[@class='catalog-navigation-classifier__item']//*[contains(@class,'title-wrapper')]");
    private static final By GROUP_MENU_LOCATOR = By.xpath("//*[contains(@class,'vue-notification-group')]");
    private static final TopMenuEnum MENU_ELEMENT = TopMenuEnum.CATALOG;

    public MobileCatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void openPage() {
        WaitUtil.waitUntilElementVisible(webDriver, GROUP_MENU_LOCATOR, 10);
        webDriver.findElement(GROUP_MENU_LOCATOR).click();
        topMenu.clickOnItem(MENU_ELEMENT);
    }

    @Override
    public boolean isOpened() {
        WaitUtil.waitUntilElementVisible(webDriver, CATALOG_PAGE_LOCATOR, DEFAULT_PAGE_WAIT_IN_SECOND);
        return webDriver.findElements(CATALOG_PAGE_LOCATOR).size() > 0;
    }

    @Override
    public List<String> getMenuList() {
        WaitUtil.waitForElementsCount(webDriver,MENU_ITEMS_LOCATOR,9);
        List<WebElement> menus = webDriver.findElements(MENU_ITEMS_LOCATOR);
        return menus.stream().map(webElement -> webElement.getText()).toList();
    }
}
