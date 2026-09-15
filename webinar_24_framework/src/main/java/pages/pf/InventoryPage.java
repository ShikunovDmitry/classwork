package pages.pf;

import org.openqa.selenium.support.PageFactory;
import webdriver.Browser;

public class InventoryPage {

    public InventoryPage() {
      PageFactory.initElements(Browser.getDriver(), this);
    }

    public boolean isOpened() {
      return Browser.getDriver().getCurrentUrl().contains("inventory");
    }
}
