package com.itacademy.aqa.framework.tests;

import com.itacademy.aqa.framework.runner.BaseTest;
import com.itacademy.aqa.framework.webdriver.Browser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTest extends BaseTest {

    @Test
    public void testAcceptAlert(){
        Browser.getDriver().get("https://www.quackit.com/javascript/javascript_alert_box.cfm");

        Browser.getJsExecutor().executeScript("window.scrollBy(0,200)");

        Browser.getDriver().switchTo().frame("result1");
        WebElement  button = Browser.getDriver().findElement(By.xpath("//input[@value='Click me']"));
        button.click();

        Alert alert = Browser.getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "Thanks... I feel much better now!");
        alert.accept();
        Browser.getDriver().switchTo().defaultContent();
        Browser.takeSceenShot();

    }
}
