package com.itacademy.aqa.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest {

    public static final By SEARCH_FIELD_LOCATOR = By.name("qa");
    WebDriver webDriver;

    @BeforeMethod
    public void driverInitialize() {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com/");
    }

    @Test
    public void googleTest() {


//        WebElement searchField = webDriver.findElement(By.xpath("//*[@name='q']"));
        WebElement searchField = webDriver.findElement(SEARCH_FIELD_LOCATOR);
        searchField.sendKeys("Selenium");
        searchField.submit();
    }

    @Test
    public void searcItAcademyTest() {
//        WebElement searchField = webDriver.findElement(By.xpath("//*[@name='q']"));
        List<WebElement> searchFields = webDriver.findElements(SEARCH_FIELD_LOCATOR);
        Assert.assertTrue(searchFields.size()>0, "No elements found bu locator" + SEARCH_FIELD_LOCATOR);
        searchFields.get(0).sendKeys("It Academy");
        searchFields.get(0).submit();
    }

    @AfterMethod
    public void cleanup() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
