package com.itacademy.aqa.tests;

import com.itacademy.aqa.webdriver.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeMethod
    public void driverInitialize() {
        webDriver = Browser.getDriver();
        System.out.printf("");

    }

    @AfterMethod
    public void cleanup() {
        Browser.close();
    }
}
