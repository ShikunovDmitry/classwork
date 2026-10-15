package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.ScreenshotUtil;

public class CartPageElement {

  private WebElement containerWebElement;

  private final By linkLocator = By.xpath(".//a");
  private final By removeButtonLocator = By.xpath(".//button");

  public CartPageElement(WebElement containerWebElement) {
    this.containerWebElement = containerWebElement;
  }

  public String getElementLinkText() {
    return containerWebElement.findElement(linkLocator).getText();
  }

  public void removeElement(){
    ScreenshotUtil.takeElementScreenshot(containerWebElement,"carItem");
    containerWebElement.findElement(removeButtonLocator).click();

  }

}
