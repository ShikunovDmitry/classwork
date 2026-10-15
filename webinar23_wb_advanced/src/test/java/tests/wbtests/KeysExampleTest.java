package tests.wbtests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class KeysExampleTest extends BaseWebDriverTest {


  @Test
  public void senKeyTest(){
    driver.get("https://www.saucedemo.com/");

    WebElement userName = driver.findElement(By.id("user-name"));
    userName.sendKeys("standard_user");
    WebElement password = driver.findElement(By.id("password"));
    password.sendKeys("secret_sauce");
    password.sendKeys(Keys.TAB, Keys.ENTER);
   // password.sendKeys(Keys.ENTER);

    wait.until(ExpectedConditions.urlContains("inventory"));

  }

  @Test
  public void alertTest(){
    driver.get(BASE_URL + "/javascript_alerts");
    driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
    Alert alert = driver.switchTo().alert();
    System.out.println(alert.getText());
    alert.accept();

  }

  @Test
  public void alertOkTest(){
    driver.get(BASE_URL + "/javascript_alerts");
    driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    System.out.println(alert.getText());
    alert.accept();
    WebElement result = wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.id("result")));
    System.out.println(result.getText());

  }
  @Test
  public void alertDismissTest(){
    driver.get(BASE_URL + "/javascript_alerts");
    driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    System.out.println(alert.getText());
    alert.dismiss();
    WebElement result = wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.id("result")));
    System.out.println(result.getText());

  }

  @Test
  public void alertPromptTest(){
    driver.get(BASE_URL + "/javascript_alerts");
    driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    System.out.println(alert.getText());
    alert.sendKeys("Hello World");
    alert.accept();
    WebElement result = wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.id("result")));
    System.out.println(result.getText());

  }

  @Test
  public void frameTest(){
    driver.get(BASE_URL + "/iframe");
    System.out.println("Before Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
    driver.switchTo().frame(0);
    System.out.println("In Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
    driver.switchTo().defaultContent();
    System.out.println("After Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
  }
  @Test
  public void frameIdTest(){
    driver.get(BASE_URL + "/iframe");
    System.out.println("Before Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
    driver.switchTo().frame("mce_0_ifr");
    System.out.println("In Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
    driver.switchTo().defaultContent();
    System.out.println("After Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
  }
  @Test
  public void frameWebElementTest(){
    driver.get(BASE_URL + "/iframe");
    System.out.println("Before Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
    WebElement frame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mce_0_ifr")));
    driver.switchTo().frame(frame);
    System.out.println("In Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
    driver.switchTo().defaultContent();
    System.out.println("After Frame element found:" + (driver.findElements(By.id("tinymce")).size()>0));
  }





}
