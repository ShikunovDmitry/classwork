package tests.potests;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import it.academy.pages.po.LoginPage;
import it.academy.utilities.ConfigReader;
import it.academy.webdriver.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;
import it.academy.pages.po.InventoryPage;
import tests.BaseTest;


public class LoginTest extends BaseTest {


  @Test()
  @TmsLink("TMS-2201")
  @Issue("TMS-3023")
  @Link(name = "Documentation", url = "https//google.com")
  @Epic("Epic")
  @Feature("Feature")
  @Story("Story")
  @Owner("Qa-team")
  @Severity(SeverityLevel.CRITICAL)
  @Tag("allureTag")
  @Tag("Smoke")
  public void loginTest() {
    LoginPage loginPage = new LoginPage();
    loginPage.open();
    loginPage.doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
    InventoryPage inventoryPage = new InventoryPage();

    Assert.assertTrue(inventoryPage.isOpened(), "Inventory page is not displayed");
    Browser.takeScreenShot();

  }
}
