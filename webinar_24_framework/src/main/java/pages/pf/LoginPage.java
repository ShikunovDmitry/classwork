package pages.pf;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webdriver.Browser;

public class LoginPage {
  private final String baseUrl = "https://www.saucedemo.com";
  @FindBy(id = "user-name")
  public WebElement userNameField;
  @FindBy(id = "password")
  public WebElement passwordField;
  @FindBy(id = "login-button")
  public WebElement loginButton;

  public LoginPage() {
    PageFactory.initElements(Browser.getDriver(), this);
  }

  public void open() {
    Browser.getDriver().get(baseUrl);
  }

  public void doLogin(String userName, String password) {
    enterUserName(userName);
    enterPassword(password);
    clickLoginButton();
  }

  public void clickLoginButton() {
    this.loginButton.click();
  }

  public void enterUserName(String userName) {
    this.userNameField.sendKeys(userName);
  }

  public void enterPassword(String password) {
    this.passwordField.sendKeys(password);
  }
}
