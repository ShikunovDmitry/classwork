package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import webdriver.Browser;

import java.time.Duration;

public class UploadTest extends BaseTest{
  @Test(description = "File upload")
  public void testFileUpload() {
    Browser.getDriver().navigate().to("https://the-internet.herokuapp.com/upload");

    // Method 1: sendKeys with file path (works for standard input[type=file])
    String filePath = System.getProperty("user.dir") +
        "/src/test/resources/test-upload.txt";

    WebElement uploadInput = Browser.getDriver().findElement(By.id("file-upload"));
    uploadInput.sendKeys(filePath);

    Browser.getDriver().findElement(By.id("file-submit")).click();

    WebElement successMessage = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("h3")));

    Assert.assertEquals(successMessage.getText(), "File Uploaded!");
    System.out.println("File uploaded successfully");
  }

}
