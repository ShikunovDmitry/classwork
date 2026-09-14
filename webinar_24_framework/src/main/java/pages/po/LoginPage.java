package pages.po;

import org.openqa.selenium.By;
import webdriver.Browser;

public class LoginPage extends BasePage {

  private static final By userNameLocator = By.id("user-name");
  private static final By passwordLocator = By.id("password");
  private static final By loginButtonLocator = By.id("login-button");


  public void open(){
    Browser.getDriver().get(baseUrl);
  }

  public void doLogin(String userName, String password) {
    enterUserName(userName);
    enterPassword(password);
    clickLoginButton();
  }

  public void clickLoginButton() {
    Browser.findVisibleElement(loginButtonLocator).click();
  }
  public void enterUserName(String userName) {

    Browser.findVisibleElement(userNameLocator).sendKeys(userName);
  }
  public void enterPassword(String password) {
    Browser.findVisibleElement(passwordLocator).sendKeys(password);
  }
}
