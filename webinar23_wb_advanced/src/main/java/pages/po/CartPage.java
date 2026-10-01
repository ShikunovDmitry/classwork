package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CartPage extends BasePage {

  private final By itemLocator = By.xpath("//*[@data-test='inventory-item']");

  public CartPage(WebDriver driver) {
    super(driver);
  }

  public List<CartPageElement> getItems() {
    List<WebElement> items = driver.findElements(itemLocator);

    List<CartPageElement> cartPageElements = new ArrayList<>();

    items.forEach(item -> {cartPageElements.add(new CartPageElement(item));});

    return cartPageElements;
  }

  public boolean removeItemFromCart(String itemName) {
    AtomicBoolean itemRemoved = new AtomicBoolean(false);
    getItems().stream().forEach(cartItem -> {
      if (cartItem.getElementLinkText().equals(itemName)) {
        cartItem.removeElement();
        itemRemoved.set(true);
      }
    });
    return itemRemoved.get();
  }

  public boolean isOpened() {
    return findExistElement(itemLocator)!=null;
  }
}
