package com.itacademy.aqa.cache;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class AppDeleteAllByDriver
{
    public static void main( String[] args )
    {
       System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.google.com/");
        Set<Cookie> cookieSet = webDriver.manage().getCookies();
        System.out.println(cookieSet.size());

        webDriver.manage().deleteAllCookies();
        System.out.println("Deleting cookies");
        System.out.println(webDriver.manage().getCookies().size());

        webDriver.quit();
        System.out.println("Exit driver");
    }
}
