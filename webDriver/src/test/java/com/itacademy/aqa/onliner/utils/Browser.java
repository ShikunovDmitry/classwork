package com.itacademy.aqa.onliner.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    static {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
    }

    private static int DEFAULT_WAIT_IN_SECONDS = 30;

    private static WebDriver driver;

    private Browser() {
    }

    public static void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_IN_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        if (driver == null) {
            driver.quit();
            driver = null;
        }
    }
}
