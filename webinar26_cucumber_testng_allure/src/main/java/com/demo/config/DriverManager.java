package com.demo.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Thread-safe WebDriver manager using ThreadLocal pattern
 * Supports Chrome, Firefox, Edge browsers
 */
public class DriverManager {

  private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

  // ThreadLocal ensures each test thread has its own WebDriver instance
  private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  private DriverManager() {
    // Utility class - prevent instantiation
  }

  /**
   * Initialize WebDriver based on configuration
   */
  public static void initDriver() {
    String browser = ConfigReader.get("browser", "chrome");
    boolean headless = Boolean.parseBoolean(ConfigReader.get("headless", "false"));
    int width = Integer.parseInt(ConfigReader.get("browser.width", "1920"));
    int height = Integer.parseInt(ConfigReader.get("browser.height", "1080"));

    log.info("Initializing {} driver (headless: {})", browser, headless);

    WebDriver driver = switch (browser.toLowerCase()) {
      case "firefox" -> createFirefoxDriver(headless);
      case "edge" -> createEdgeDriver(headless);
      default -> createChromeDriver(headless);
    };

    // Configure timeouts
    driver.manage().timeouts()
        .implicitlyWait(Duration.ofSeconds(
            Long.parseLong(ConfigReader.get("implicit.wait", "10"))))
        .pageLoadTimeout(Duration.ofSeconds(
            Long.parseLong(ConfigReader.get("page.load.timeout", "30"))));

    driver.manage().window().setSize(
        new org.openqa.selenium.Dimension(width, height));

    driverThreadLocal.set(driver);
    log.info("WebDriver initialized successfully for thread: {}",
        Thread.currentThread().getName());
  }

  public static WebDriver getDriver() {
    WebDriver driver = driverThreadLocal.get();
    if (driver == null) {
      throw new IllegalStateException(
          "WebDriver not initialized. Call initDriver() first.");
    }
    return driver;
  }

  public static void quitDriver() {
    WebDriver driver = driverThreadLocal.get();
    if (driver != null) {
      log.info("Quitting WebDriver for thread: {}",
          Thread.currentThread().getName());
      driver.quit();
      driverThreadLocal.remove();
    }
  }

  private static WebDriver createChromeDriver(boolean headless) {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");
    if (headless) {
      options.addArguments("--headless=new");
    }
    return new ChromeDriver(options);
  }

  private static WebDriver createFirefoxDriver(boolean headless) {
    WebDriverManager.firefoxdriver().setup();
    FirefoxOptions options = new FirefoxOptions();
    if (headless) {
      options.addArguments("--headless");
    }
    return new FirefoxDriver(options);
  }

  private static WebDriver createEdgeDriver(boolean headless) {
    WebDriverManager.edgedriver().setup();
    EdgeOptions options = new EdgeOptions();
    if (headless) {
      options.addArguments("--headless=new");
    }
    return new EdgeDriver(options);
  }
}