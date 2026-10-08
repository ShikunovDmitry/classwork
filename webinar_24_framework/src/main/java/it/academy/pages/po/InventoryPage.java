package it.academy.pages.po;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import it.academy.webdriver.Browser;

public class InventoryPage extends BasePage {
  private static final Logger logger = LogManager.getLogger(CartPage.class);

  private final By productLabelLocator = By.xpath("//span[contains(text(),'Product')]");
  private final By cartLinkLocator = By.xpath("//a[@data-test='shopping-cart-link']");

  String addToCartButtonPattern = "//*[contains(text(),'%s')]/../../..//button";

 // public boolean isOpened() {
 //   return driver.getCurrentUrl().contains("inventory");
 // }

  public boolean isOpened(){
//    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    webDriverWait
//        .until(ExpectedConditions.visibilityOfElementLocated(productLabelLocator));

    if(!Browser.getDriver().findElements(productLabelLocator).isEmpty()){
      logger.info("Inventory page is opened");
      return true;
    } else  {
      logger.info("Inventory page is not opened");
      return false;
    }
  }

  public void open() {
    Browser.getDriver().get(baseUrl + "/inventory");
  }

  @Step("Add {itemName} to cart")
  //Sauce Labs Backpack
  public void addItemToCart(String itemName) {

    WebElement addToCurtButton = Browser.findVisibleElement(By.xpath(String.format(addToCartButtonPattern, itemName)));
    addToCurtButton.click();

  }

  public void goToCart() {
    Browser.findVisibleElement(cartLinkLocator).click();
  }
}
