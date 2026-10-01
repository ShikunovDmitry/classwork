package tests.potests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.po.InventoryPage;
import pages.po.LoginPage;
import tests.BaseTest;


public class LoginTest extends BasePOTest {


  @Test
  public void loginTest() {
    InventoryPage inventoryPage = new InventoryPage(driver);

    Assert.assertTrue(inventoryPage.isOpened(), "Inventory page is not displayed");

  }
}
