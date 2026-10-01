package tests.potests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.po.InventoryPage;
import pages.po.LoginPage;
import tests.BaseTest;


public class LoginTest extends BaseTest {

  @Test
  public void loginTest() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();

    loginPage.doLogin("standard_user", "secret_sauce");
    InventoryPage inventoryPage = new InventoryPage(driver);

    Assert.assertTrue(inventoryPage.isOpened(), "Inventory page is not displayed");
    inventoryPage.getItems().forEach(item -> {item.addToCart();});

  }
}
