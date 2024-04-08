package com.itacademy.aqa.steam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SteamAboutPage {
    @FindBy(xpath = "//*[contains(@class,'about_install_steam_link')]")
    WebElement downloadSteamButton;

    public SteamAboutPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public boolean isOpened() {
        return downloadSteamButton.isDisplayed();
    }
}
