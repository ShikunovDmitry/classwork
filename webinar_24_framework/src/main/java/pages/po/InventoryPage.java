package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.Browser;

public class InventoryPage extends BasePage {

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
      return true;
    } else  {
      return false;
    }
  }

  public void open() {
    Browser.getDriver().get(baseUrl + "/inventory");
  }

  //Sauce Labs Backpack
  public void addItemToCart(String itemName) {

    WebElement addToCurtButton = Browser.findVisibleElement(By.xpath(String.format(addToCartButtonPattern, itemName)));
    addToCurtButton.click();

  }

  public void goToCart() {
    Browser.findVisibleElement(cartLinkLocator).click();
  }
}
