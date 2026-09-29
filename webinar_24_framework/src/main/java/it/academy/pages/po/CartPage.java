package it.academy.pages.po;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import it.academy.webdriver.Browser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartPage extends BasePage {

  private static final Logger logger = LogManager.getLogger(CartPage.class);

  private final By itemLocator = By.xpath("//*[@data-test='inventory-item']");


  public List<CartPageElement> getItems() {

    logger.info("Getting CartPage items");
    List<WebElement> items = Browser.getDriver().findElements(itemLocator);

    List<CartPageElement> cartPageElements = new ArrayList<>();

    items.forEach(item -> {
      cartPageElements.add(new CartPageElement(item));});

    return cartPageElements;
  }

  public boolean removeItemFromCart(String itemName) {
    logger.info("Removing item from cart: " +  itemName);
    AtomicBoolean itemRemoved = new AtomicBoolean(false);
    getItems().stream().forEach(cartItem -> {
      if (cartItem.getElementLinkText().equals(itemName)) {
        cartItem.removeElement();
        itemRemoved.set(true);
      } else {
        logger.debug("Not an item we need: " +  cartItem.getElementLinkText());
      }
    });
    return itemRemoved.get();
  }

  public boolean isOpened() {
    logger.info("Checking if CartPage is opened");
    return Browser.findExistElement(itemLocator)!=null;
  }
}
