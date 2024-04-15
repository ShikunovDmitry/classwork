package com.itacademy.aqa.framework.runner;

import com.itacademy.aqa.framework.configuration.Configuration;
import com.itacademy.aqa.framework.webdriver.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver webDriver;

    @BeforeMethod
    public void driverInitialize() {
        System.out.println("test is run by " + Configuration.getUserNickName());
        webDriver = Browser.getDriver();
    }

    @AfterMethod
    public void cleanup() {
        Browser.close();
    }
}
