package com.itacademy.aqa.framework.tests;

import com.itacademy.aqa.framework.configuration.Configuration;
import com.itacademy.aqa.framework.runner.BaseTest;
import com.itacademy.aqa.framework.ui.onliner.pageFactory.NewsPageFactory;
import com.itacademy.aqa.framework.ui.onliner.pages.ICatalogPage;
import com.itacademy.aqa.framework.ui.onliner.pages.mobile.MobileCatalogPage;
import com.itacademy.aqa.framework.ui.onliner.pages.web.WebCatalogPage;
import com.itacademy.aqa.framework.ui.onliner.pages.Page;
import com.itacademy.aqa.framework.webdriver.Browser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class OnlinerPageObjectTest extends BaseTest {

    @BeforeMethod
    public void goToOnliner() {
        webDriver.get("https://www.onliner.by/");
    }

    @Test
    public void testCatalogPageCanBeOpened() {
        ICatalogPage catalogPage;
        if (Configuration.getBrowserType().getType().equalsIgnoreCase("web")) {
            catalogPage = new WebCatalogPage(webDriver);
        } else {
            catalogPage = new MobileCatalogPage(webDriver);
        }

        catalogPage.openPage();

        Assert.assertTrue(catalogPage.isOpened(),
                "Catalog page isn't opened after clicking by Catalog menu item");
    }
    @Test
    public void testMenuItems() {
        ICatalogPage catalogPage;
        if (Configuration.getBrowserType().getType().equalsIgnoreCase("web")) {
            catalogPage = new WebCatalogPage(webDriver);
        } else {
            catalogPage = new MobileCatalogPage(webDriver);
        }

        catalogPage.openPage();

        List<String> actualMenuItems = catalogPage.getMenuList();

        Assert.assertTrue(actualMenuItems.contains("Стройка и ремонт"));
    }

    @Test
    public void testNewsPageCanBeOpened() {

        Page newsPage = new NewsPageFactory(webDriver);

        newsPage.openPage();

        Assert.assertTrue(newsPage.isOpened(),
                "News page isn't opened after clicking by Catalog menu item");
    }
}
