package com.demo.context;

import com.demo.config.DriverManager;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class TestContext {
  // Page Objects - lazily initialized
  private LoginPage loginPage;
  private HomePage homePage;

  public WebDriver getDriver() {
    return DriverManager.getDriver();
  }

  // ==================== Page Object Factory ====================
  // Lazy initialization - pages created only when needed
  public HomePage getHomePage() {
    if (homePage == null) {
      homePage = new HomePage(getDriver());
    }
    return homePage;
  }

  public LoginPage getLoginPage() {
    if (loginPage == null) {
      loginPage = new LoginPage(getDriver());
    }
    return loginPage;
  }
}
