package com.demo.context;

import com.demo.config.DriverManager;
import com.demo.pages.HomePage;
import com.demo.pages.LoginPage;
import com.demo.pages.ProductPage;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
  // Page Objects - lazily initialized
  private LoginPage loginPage;
  private HomePage homePage;
  private ProductPage productPage;

  // Shared scenario data - accessible across all step classes
  private final Map<String, Object> scenarioData = new HashMap<>();

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
  public <T> T getData(String key) {
    return (T) scenarioData.get(key);
  }
  public void setData(String key, Object value) {
    scenarioData.put(key, value);
  }
  public boolean hasData(String key) {
    return scenarioData.containsKey(key);
  }

  public ProductPage getProductPage() {
    if (productPage == null) productPage = new ProductPage(getDriver());
    return productPage;
  }
}
