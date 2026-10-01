package pages.po;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

  public InventoryPage(WebDriver driver) {
    super(driver);
  }

  public boolean isOpened() {
    return driver.getCurrentUrl().contains("inventory");
  }

  public void open() {
    driver.get(baseUrl + "/inventory");
  }

  public List<Item> getItems() {
    return new ArrayList<>();
  }
}
