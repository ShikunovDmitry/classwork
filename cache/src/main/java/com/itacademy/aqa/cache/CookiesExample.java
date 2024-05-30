package com.itacademy.aqa.cache;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CookiesExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        System.out.println(webDriver.manage().getCookies().size());

        webDriver.get("https://www.google.com/");

        Cookie cookie = new Cookie.Builder("authorization", "token")
                .domain("www.google.com")
                .path("/")
                .isSecure(true)
                .expiresOn(Date.from(LocalDateTime.now().plusDays(5).atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        webDriver.manage().addCookie(cookie);

        System.out.println(webDriver.manage().getCookies().size());
        webDriver.quit();
        System.out.println("Exit driver");
    }
}
