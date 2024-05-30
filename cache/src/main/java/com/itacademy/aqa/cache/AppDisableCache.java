package com.itacademy.aqa.cache;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AppDisableCache
{
    public static void main( String[] args )
    {
       System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*","disable-cache");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.google.com/");
        System.out.println(webDriver.manage().getCookies().size());
        webDriver.quit();
        System.out.println("Exit driver");
    }
}
