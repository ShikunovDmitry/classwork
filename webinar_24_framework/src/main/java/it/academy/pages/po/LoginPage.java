package it.academy.pages.po;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import it.academy.webdriver.Browser;

public class LoginPage extends BasePage {
  private static final Logger logger = LogManager.getLogger(LoginPage.class);

  private static final By userNameLocator = By.id("user-name");
  private static final By passwordLocator = By.id("password");
  private static final By loginButtonLocator = By.id("login-button");

  public void open(){
    logger.info(String.format("Opening login page by url: %s", this.baseUrl));
    Browser.getDriver().get(baseUrl);
  }

  @Step("Do login as {userName} and {password}")
  public void doLogin(String userName, String password) {
    Allure.addAttachment("userName", userName);
    logger.info("Logged in as " + userName);
    enterUserName(userName);
    enterPassword(password);
    clickLoginButton();
  }

  @Step("Click login button")
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
