package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

  private static final String SCREENSHOT_DIR = "screenshots/";

  public static void takeScreenshot(WebDriver driver, String testName) {

    String timestamp  = getDateTimeFormatted();
    String fileName   = testName + "_" + timestamp + ".png";
    String filePath   = SCREENSHOT_DIR + fileName;

    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
    File dest = new File(filePath);
    try {
      FileUtils.copyFile(screenshot, dest);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public static String takeElementScreenshot(WebElement element, String elementName) {
    String timestamp = getDateTimeFormatted();
    String filePath  = SCREENSHOT_DIR + elementName + "_" + timestamp + ".png";

    try {
      File srcFile = element.getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(srcFile, new File(filePath));
      return filePath;
    } catch (IOException e) {
      System.err.println("Element screenshot failed: " + e.getMessage());
      return null;
    }
  }

  private static String getDateTimeFormatted(){
    return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
  }
}
