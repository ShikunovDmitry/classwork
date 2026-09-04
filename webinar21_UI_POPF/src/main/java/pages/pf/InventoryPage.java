package pages.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

  WebDriver driver;
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
      PageFactory.initElements(driver, this);
    }

    public boolean isOpened() {
      return driver.getCurrentUrl().contains("inventory");
    }
}
