package tests.potests;

import org.testng.annotations.BeforeMethod;
import pages.po.LoginPage;
import tests.BaseTest;
import utilities.ConfigReader;

public class BasePOTest extends BaseTest {
  @BeforeMethod
  public void login() {
    LoginPage loginPage = new LoginPage();
    loginPage.open();

    loginPage.doLogin(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
  }
}
