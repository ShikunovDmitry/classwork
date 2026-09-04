package pages.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    findElement(loginButtonLocator).click();
  }
  public void enterUserName(String userName) {

    findElement(userNameLocator).sendKeys(userName);
  }
  public void enterPassword(String password) {
    findElement(passwordLocator).sendKeys(password);
  }

  protected WebElement findElement(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
}
