package pages.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
  private final String baseUrl = "https://www.saucedemo.com";
  @FindBy(id = "user-name")
  public WebElement userNameField;
  @FindBy(id = "password")
  public WebElement passwordField;
  @FindBy(id = "login-button")
  public WebElement loginButton;

  private WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void open() {
    driver.get(baseUrl);
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
