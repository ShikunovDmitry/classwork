package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
    containerWebElement.findElement(removeButtonLocator).click();
  }

}
