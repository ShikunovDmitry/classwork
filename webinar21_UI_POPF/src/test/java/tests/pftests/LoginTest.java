package tests.pftests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.pf.InventoryPage;
import pages.pf.LoginPage;
import tests.BaseTest;


public class LoginTest extends BaseTest {

  @Test
  public void testLogin() {

    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();

    loginPage.doLogin("standard_user", "secret_sauce");
    InventoryPage inventoryPage = new InventoryPage(driver);

    Assert.assertTrue(inventoryPage.isOpened(), "Inventory page is not displayed");

  }
}
