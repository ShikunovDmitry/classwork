package com.itacademy.aqa.framework.tests;

import com.itacademy.aqa.framework.runner.BaseTest;
import com.itacademy.aqa.framework.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {

    @Test
    public void testDragAndDrop(){
        Browser.getDriver().get("http://jqueryui.com/draggable/#sortable");

        WebElement frame = Browser.getDriver().findElement(By.className("demo-frame"));
        Browser.getDriver().switchTo().frame(frame);

        WebElement source = Browser.getDriver().findElement(By.id("draggable"));
//        WebElement target = Browser.getDriver().findElements(By.xpath("//*[@id='sortable']/li")).get(2);
//        WebElement target = Browser.getDriver().findElement(By.xpath("//*[@id='sortable']/li[3]"));
        WebElement target = Browser.getDriver().findElement(By.id("sortable")).findElement(By.xpath("./li[3]"));

        Actions actions = new Actions(Browser.getDriver());

        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .build().perform();
        Browser.takeSceenShot();
        Browser.getDriver().switchTo().defaultContent();
    }
    @Test
    public void testDragAndDrop2(){
        Browser.getDriver().get("https://jqueryui.com/droppable/");

        WebElement frame = Browser.getDriver().findElement(By.className("demo-frame"));
        Browser.getDriver().switchTo().frame(frame);

        WebElement source = Browser.getDriver().findElement(By.id("draggable"));
        WebElement target = Browser.getDriver().findElement(By.id("droppable"));

        Actions actions = new Actions(Browser.getDriver());

        actions.clickAndHold(source)
                .moveToElement(target)
                .release()
                .build().perform();
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(),5);
        wait.until(driver -> driver.findElement(By.id("droppable")).getCssValue("background-color")
                .equalsIgnoreCase("rgba(255, 250, 144, 1)"));
        Assert.assertEquals(target.getCssValue("background-color"),"rgba(255, 250, 144, 1)");
        Browser.takeSceenShot();
        Browser.getDriver().switchTo().defaultContent();
    }
}
