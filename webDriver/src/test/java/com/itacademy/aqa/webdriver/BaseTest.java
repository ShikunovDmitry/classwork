package com.itacademy.aqa.webdriver;

import com.itacademy.aqa.onliner.utils.Browser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeMethod
    public void driverInitialize() {
        webDriver = Browser.getDriver();

    }

    @AfterMethod
    public void cleanup() {
        Browser.close();
    }
}
