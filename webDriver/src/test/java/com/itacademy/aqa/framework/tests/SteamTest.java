package com.itacademy.aqa.framework.tests;

import com.itacademy.aqa.framework.runner.BaseTest;
import com.itacademy.aqa.framework.ui.SteamAboutPage;
import com.itacademy.aqa.framework.ui.SteamMainPage;
import com.itacademy.aqa.framework.webdriver.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class SteamTest extends BaseTest {
    public static By NAV_MENU_LOCATOR = By.xpath("//*[contains(@class,'store_nav_bg')]//*[contains(@class,'tab')][not(@style)]");

    @BeforeMethod

    public void navigateToSteam() {
        webDriver.get("https://store.steampowered.com/");
    }

    @Test
    public void testVerifyNumbersOfElementsInMenu() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.numberOfElementsToBe(NAV_MENU_LOCATOR, 6));

        List<WebElement> menus = webDriver.findElements(NAV_MENU_LOCATOR);

        Assert.assertEquals(menus.size(), 6, " Number of menu items is not the same as expected");
    }

    @Test
    public void testVerifyNumbersOfElementsInMenuByFluentWait() {
        FluentWait<WebDriver> wait = new FluentWait<>(webDriver);
        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(ElementNotInteractableException.class);

        wait.until(webDriver1 -> webDriver1.findElements(NAV_MENU_LOCATOR).size() > 5);

        List<WebElement> menus = webDriver.findElements(NAV_MENU_LOCATOR);

        Assert.assertEquals(menus.size(), 6, " Number of menu items is not the same as expected");
    }

    @Test
    public void testSteamInstallPageCanBeOpened() {
        SteamMainPage steamMainPage = new SteamMainPage(webDriver);

        Assert.assertTrue(steamMainPage.isOpened(), "Steam page isn't opened");

        SteamAboutPage steamAboutPage = steamMainPage.openInstallmentPage();

//        SteamAboutPage steamAboutPage = new SteamAboutPage(webDriver);

        Assert.assertTrue(steamAboutPage.isOpened(), "About page isn't opened");

        Browser.takeSceenShot();

    }

    @Test
    public void testSteamCanBeDownloaded() {

        String downloadDirectory = "C:\\Users\\Dzmitry_Shykunou\\Downloads\\";

        String downloadPath = downloadDirectory + "SteamSetup.exe";

        File fileToCheck = new File(downloadPath);
        if (fileToCheck.exists()) {
            fileToCheck.delete();
        }
        SteamMainPage steamMainPage = new SteamMainPage(webDriver);

        Assert.assertTrue(steamMainPage.isOpened(), "Steam page isn't opened");

        SteamAboutPage steamAboutPage = steamMainPage.openInstallmentPage();

        Assert.assertTrue(steamAboutPage.isOpened(), "About page isn't opened");

        steamAboutPage.downloadSteam();

        FluentWait<File> wait = new FluentWait<>(fileToCheck);

        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofSeconds(5));
        wait.until(file -> file.exists() && file.length() > 0);

        Assert.assertTrue(fileToCheck.exists(), "File wasn't downloaded by path " + downloadPath);


    }
}
