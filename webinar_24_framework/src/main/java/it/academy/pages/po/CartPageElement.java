package it.academy.pages.po;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPageElement {
  private static final Logger logger = LogManager.getLogger(CartPageElement.class);

  private WebElement containerWebElement;

  private final By linkLocator = By.xpath(".//a");
  private final By removeButtonLocator = By.xpath(".//button");

  public CartPageElement(WebElement containerWebElement) {
    logger.debug(String.format("CartPageElement: %s", containerWebElement.getText()));
    this.containerWebElement = containerWebElement;
  }

  public String getElementLinkText() {
    logger.debug(String.format("getElementLinkText"));
    return containerWebElement.findElement(linkLocator).getText();
  }

  public void removeElement(){
    containerWebElement.findElement(removeButtonLocator).click();
  }

}
