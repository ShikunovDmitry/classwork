package com.demo.pages;

import com.demo.config.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  // Also using By locators for dynamic lookups
  private static final By USERNAME_FIELD = By.id("user-name");
  private static final By PASSWORD_FIELD = By.id("password");
  private static final By LOGIN_BUTTON = By.id("login-button");
  private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
  private static final By ERROR_CLOSE_BUTTON = By.cssSelector(".error-button");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Step("Open login page")
  public LoginPage open() {
    navigateTo(ConfigReader.getBaseUrl());
    return this;
  }

  @Step("Enter username: {username}")
  public LoginPage enterUsername(String username) {
    log.info("Entering username: {}", username);
    type(USERNAME_FIELD, username);
    return this;
  }

  @Step("Enter password")
  public LoginPage enterPassword(String password) {
    log.info("Entering password");
    type(PASSWORD_FIELD, password);
    return this;
  }

  @Step("Click login button")
  public void clickLogin() {
    click(LOGIN_BUTTON);
  }

  @Step("Login with credentials: {username}")
  public void login(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLogin();
  }

  @Step("Get error message text")
  public String getErrorMessage() {
    return getText(ERROR_MESSAGE);
  }

  @Step("Check if error message is displayed")
  public boolean isErrorDisplayed() {
    return isDisplayed(ERROR_MESSAGE);
  }

  @Step("Close error message")
  public void closeError() {
    click(ERROR_CLOSE_BUTTON);
  }

  public boolean isLoginButtonEnabled() {
    return isEnabled(LOGIN_BUTTON);
  }

  public boolean isOnLoginPage() {
    return isDisplayed(LOGIN_BUTTON);
  }
}