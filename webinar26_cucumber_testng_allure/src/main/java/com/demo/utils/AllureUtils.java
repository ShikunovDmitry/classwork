package com.demo.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility class for Allure report enhancements
 */
public class AllureUtils {

  private static final Logger log = LoggerFactory.getLogger(AllureUtils.class);

  private AllureUtils() {}

  /**
   * Attach screenshot to Allure report
   */
  public static void attachScreenshot(WebDriver driver, String name) {
    try {
      byte[] screenshot = ((TakesScreenshot) driver)
          .getScreenshotAs(OutputType.BYTES);
      Allure.addAttachment(name, "image/png",
          new ByteArrayInputStream(screenshot), ".png");
      log.info("Screenshot attached: {}", name);
    } catch (Exception e) {
      log.error("Failed to take screenshot: {}", e.getMessage());
    }
  }

  /**
   * Attach text content to Allure report
   */
  public static void attachText(String name, String content) {
    Allure.addAttachment(name, "text/plain", content);
  }

  /**
   * Attach HTML content to Allure report
   */
  public static void attachHtml(String name, String htmlContent) {
    Allure.addAttachment(name, "text/html", htmlContent);
  }

  /**
   * Attach JSON content to Allure report
   */
  public static void attachJson(String name, String jsonContent) {
    Allure.addAttachment(name, "application/json", jsonContent);
  }

  /**
   * Add parameter to current test in Allure
   */
  public static void addParameter(String name, String value) {
    Allure.parameter(name, value);
  }

  /**
   * Add description to current test
   */
  public static void addDescription(String description) {
    Allure.description(description);
  }

  /**
   * Add step to Allure report
   */
  public static void step(String stepName) {
    Allure.step(stepName);
  }

  /**
   * Attach page source to Allure report
   */
  public static void attachPageSource(WebDriver driver) {
    Allure.addAttachment("Page Source", "text/html", driver.getPageSource());
  }

  /**
   * Attach file to Allure report
   */
  public static void attachFile(String name, Path filePath) {
    try {
      byte[] content = Files.readAllBytes(filePath);
      String mimeType = Files.probeContentType(filePath);
      Allure.addAttachment(name, mimeType != null ? mimeType : "application/octet-stream",
          new ByteArrayInputStream(content),
          filePath.getFileName().toString());
    } catch (IOException e) {
      log.error("Failed to attach file: {}", e.getMessage());
    }
  }
}