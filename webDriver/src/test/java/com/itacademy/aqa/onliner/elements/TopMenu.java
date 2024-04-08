package com.itacademy.aqa.onliner.elements;

import com.itacademy.aqa.onliner.TopMenuEnum;
import com.itacademy.aqa.onliner.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopMenu {
    private static int DEFAULT_ELEMENT_WAIT_IN_SECOND = 30;
    //    By catalogMenuLocator = By.xpath("//nav//*[contains(text(),'Новости')]");
    private static final String ITEM_PATTER = "//nav//*[contains(text(),'%s')]";
    WebDriver webDriver;

    public TopMenu(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOnItem(TopMenuEnum topMenuEnum) {
        By xpath = By.xpath(String.format(ITEM_PATTER, topMenuEnum.getValue()));

        WaitUtil.waitUntilElementVisible(webDriver, xpath, DEFAULT_ELEMENT_WAIT_IN_SECOND);

        WebElement menuItemWebElement = webDriver.findElement(xpath);
        menuItemWebElement.click();

    }
}
