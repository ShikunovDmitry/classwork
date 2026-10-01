package tests.potests;

import org.testng.annotations.BeforeMethod;
import pages.po.LoginPage;
import tests.BaseTest;

public class BasePOTest extends BaseTest {
  @BeforeMethod
  public void login() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();

    loginPage.doLogin("standard_user", "secret_sauce");
  }
}
