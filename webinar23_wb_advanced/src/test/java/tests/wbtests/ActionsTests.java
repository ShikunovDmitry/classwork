package tests.wbtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import tests.BaseTest;

public class ActionsTests extends BaseWebDriverTest {
  @Test
  public void hoverTest(){
    driver.navigate().to(BASE_URL + "/hovers");

    Actions actions = new Actions(driver);

    WebElement figure1= driver.findElement(By.cssSelector(".figure:first-of-type"));

    actions.moveToElement(figure1).build().perform();
  }

  @Test
  public void doubleClickTest(){
    driver.navigate().to("https://demoqa.com/buttons");

    Actions actions = new Actions(driver);

    WebElement button= driver.findElement(By.id("doubleClickBtn"));

    actions.moveToElement(button).doubleClick().build().perform();
  }

  @Test
  public void rightClickTest(){
    driver.navigate().to("https://demoqa.com/buttons");

    Actions actions = new Actions(driver);

    WebElement button= driver.findElement(By.id("rightClickBtn"));

    actions.moveToElement(button).contextClick().build().perform();
  }

  @Test
  public void sliderTest(){
    driver.navigate().to("https://jqueryui.com/slider/");

    driver.switchTo().frame(0);

    Actions actions = new Actions(driver);

    WebElement slider= driver.findElement(By.cssSelector(".ui-slider-handle"));

    actions
        .moveToElement(slider)
        .clickAndHold()
        .moveByOffset(100, 0)
        .release()
        .build().perform();

    driver.switchTo().defaultContent();
  }

  @Test
  public void dragAndDrophoverTest(){
    driver.navigate().to(BASE_URL + "/drag_and_drop");

    Actions actions = new Actions(driver);

    WebElement figure1= driver.findElement(By.id("column-a"));
    WebElement figure2= driver.findElement(By.id("column-b"));

    //actions.dragAndDrop(figure1,figure2).build().perform();

    actions.clickAndHold(figure1)
        .moveToElement(figure2)
        .release()
        .build().perform();
  }
@Test
  public void scrollTest(){
    driver.navigate().to(BASE_URL + "/infinite_scroll");

    Actions actions = new Actions(driver);
    actions.scrollByAmount(0,5000)
        .pause(5)
    .build().perform();
  }
}
