package com.itacademy.aqa.onliner.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
    public static void waitUntilElementVisible(WebDriver driver, By locator, long timeoutInSeconds){

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
