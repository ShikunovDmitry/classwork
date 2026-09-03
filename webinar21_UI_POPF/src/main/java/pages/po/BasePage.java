package pages.po;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
  protected WebDriver driver;
  protected final String baseUrl = "https://www.saucedemo.com";

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }
}
