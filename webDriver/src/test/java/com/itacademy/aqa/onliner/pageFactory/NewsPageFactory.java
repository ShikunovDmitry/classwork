package com.itacademy.aqa.onliner.pageFactory;

import com.itacademy.aqa.onliner.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsPageFactory implements Page {

    @FindBy(xpath = "//nav//*[contains(text(),'Новости')]")
    WebElement newsMenuItem;

    @FindBy(xpath = "//*[contains(@class,'b-main-page-tabs__list')]//*[contains(text(),'Новости')]")
    WebElement newPageLocator;

    public NewsPageFactory(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @Override
    public void openPage() {
        newsMenuItem.click();
    }

    @Override
    public boolean isOpened() {
        return newPageLocator.isDisplayed();
    }
}
