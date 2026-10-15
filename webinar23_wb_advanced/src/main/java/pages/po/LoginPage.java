package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  private static final By userNameLocator = By.id("user-name");
  private static final By passwordLocator = By.id("password");
  private static final By loginButtonLocator = By.id("login-button");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void open(){
    driver.get(baseUrl);
  }

  public void doLogin(String userName, String password) {
    enterUserName(userName);
    enterPassword(password);
    clickLoginButton();
  }

  public void clickLoginButton() {
    findVisibleElement(loginButtonLocator).click();
  }
  public void enterUserName(String userName) {

    findVisibleElement(userNameLocator).sendKeys(userName);
  }
  public void enterPassword(String password) {
    findVisibleElement(passwordLocator).sendKeys(password);
  }
}
