package tests.wbtests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class JSExecutorTest extends BaseWebDriverTest{

  @Test
  public void jsExecutorLoginTest() {
    driver.navigate().to("https://www.saucedemo.com/");

    JavascriptExecutor js = (JavascriptExecutor) driver;

    WebElement username = driver.findElement(By.id("user-name"));
    WebElement password = driver.findElement(By.id("password"));
    WebElement login = driver.findElement(By.id("login-button"));

    js.executeScript("arguments[0].value = arguments[1];", username,"standard_user");
    js.executeScript("arguments[0].value = 'secret_sauce';",password);
    //password.sendKeys("secret_sauce");
    js.executeScript("arguments[0].click();", login);

  }

  @Test
  public void jsExecutorScrollTest() {
    driver.navigate().to("https://the-internet.herokuapp.com/large");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0, 500);");
  }

  @Test
  public void jsExecutorScrollIntoViewTest() {
    driver.navigate().to("https://the-internet.herokuapp.com/large");
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement element = driver.findElement(By.id("page-footer"));
    js.executeScript("arguments[0].scrollIntoView(true);", element);
  }
}
