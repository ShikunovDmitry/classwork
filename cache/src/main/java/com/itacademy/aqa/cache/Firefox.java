package com.itacademy.aqa.cache;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Firefox
{
    public static void main( String[] args )
    {
       System.setProperty("webdriver.gecko.driver","C:\\geckodriver64.exe");

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.cache.disk.enable", false);
        firefoxProfile.setPreference("browser.cache.memory.enable", false);
        firefoxProfile.setPreference("browser.cache.offline.enable", false);
        firefoxProfile.setPreference("network.http.use-cache", false);

       FirefoxDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.google.com/");

        webDriver.quit();
        System.out.println("Exit driver");
    }
}
