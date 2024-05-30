package com.itacademy.aqa.cache;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App 
{
    public static void main( String[] args )
    {
       System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        webDriver.get("https://www.google.com/");

        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");
        System.out.println(webDriver.manage().getCookies().size());
        webDriver.quit();
        System.out.println("Exit driver");
    }
}
