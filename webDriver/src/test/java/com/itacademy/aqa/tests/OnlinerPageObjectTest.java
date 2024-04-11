package com.itacademy.aqa.tests;

import com.itacademy.aqa.onliner.pageFactory.NewsPageFactory;
import com.itacademy.aqa.onliner.pages.CatalogPage;
import com.itacademy.aqa.onliner.pages.Page;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OnlinerPageObjectTest extends BaseTest{

    @BeforeMethod
    public void goToOnliner(){
        webDriver.get("https://www.onliner.by/");
    }

    @Test
    public void testCatalogPageCanBeOpened(){

        CatalogPage catalogPage = new CatalogPage(webDriver);

        catalogPage.openPage();

        Assert.assertTrue(catalogPage.isOpened(),
                "Catalog page isn't opened after clicking by Catalog menu item");
    }
    @Test
    public void testNewsPageCanBeOpened(){

        Page newsPage = new NewsPageFactory(webDriver);

        newsPage.openPage();

        Assert.assertTrue(newsPage.isOpened(),
                "News page isn't opened after clicking by Catalog menu item");
    }
}
