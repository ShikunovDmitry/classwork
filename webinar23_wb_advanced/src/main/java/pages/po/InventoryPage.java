package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.ScreenshotUtil;

public class InventoryPage extends BasePage {

  private final By productLabelLocator = By.xpath("//span[contains(text(),'Product')]");
  private final By cartLinkLocator = By.xpath("//a[@data-test='shopping-cart-link']");

  String addToCartButtonPattern = "//*[contains(text(),'%s')]/../../..//button";

  public InventoryPage(WebDriver driver) {
    super(driver);
  }

 // public boolean isOpened() {
 //   return driver.getCurrentUrl().contains("inventory");
 // }

  public boolean isOpened(){
//    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
//    webDriverWait
//        .until(ExpectedConditions.visibilityOfElementLocated(productLabelLocator));

    if(driver.findElements(productLabelLocator).size()> 0){
      return true;
    } else  {
      return false;
    }
  }

  public void open() {
    driver.get(baseUrl + "/inventory");
  }

  //Sauce Labs Backpack
  public void addItemToCart(String itemName) {

    WebElement addToCurtButton = findVisibleElement(By.xpath(String.format(addToCartButtonPattern, itemName)));
    addToCurtButton.click();


  }

  public void goToCart() {
    findVisibleElement(cartLinkLocator).click();
  }
}
