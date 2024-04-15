package com.itacademy.aqa.framework.ui;

import com.itacademy.aqa.framework.webdriver.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SteamMainPage {
    @FindBy(xpath = "//*[contains(@class,'header_installsteam_btn_content')]")
    private WebElement installButton;
    private WebDriver webDriver;

    public SteamMainPage(WebDriver webDriver) {
        PageFactory.initElements(Browser.getDriver(), this);
        this.webDriver = webDriver;
    }


    public boolean isOpened() {
        try {
            return installButton.isDisplayed();
        } catch (WebDriverException ex) {
            System.out.printf(ex.getMessage());
            return false;
        }
    }

    public SteamAboutPage openInstallmentPage() {
        installButton.click();
        return new SteamAboutPage(webDriver);
    }
}
