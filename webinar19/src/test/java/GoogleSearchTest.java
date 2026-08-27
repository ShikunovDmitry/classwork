import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // -------------------------------------------------------
    // SETUP - runs before each test
    // -------------------------------------------------------
    @BeforeEach
    public void setUp() {
        // Automatically downloads the correct ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");  // uncomment to run without UI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Explicit wait - up to 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("✅ Browser started");
    }

    // -------------------------------------------------------
    // TEST 1: Check Google page title
    // -------------------------------------------------------
    @Test
    @DisplayName("Google homepage should have correct title")
    public void testGoogleTitle() {
        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        assertEquals("Google", title, "Title should be 'Google'");
    }

    // -------------------------------------------------------
    // TEST 2: Search for something on Google
    // -------------------------------------------------------
    @Test
    @DisplayName("Search for 'Selenium WebDriver' on Google")
    public void testGoogleSearch() {
        driver.get("https://www.google.com");

        // Find the search box and type
        WebElement searchBox = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.name("q"))
        );
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.sendKeys(Keys.RETURN);

        // Wait for results to load
        wait.until(
            ExpectedConditions.titleContains("Selenium WebDriver")
        );

        String currentTitle = driver.getTitle();
        System.out.println("Results page title: " + currentTitle);

        // Verify URL contains search query
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        assertTrue(currentUrl.contains("Selenium"), 
            "URL should contain search term");
        assertTrue(currentTitle.contains("Selenium WebDriver"), 
            "Title should contain search term");
    }

    // -------------------------------------------------------
    // TEST 3: Verify search results exist
    // -------------------------------------------------------
    @Test
    @DisplayName("Search results should not be empty")
    public void testSearchResultsNotEmpty() {
        driver.get("https://www.google.com/search?q=Java+Selenium");

        // Wait for results container
        WebElement results = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id("search"))
        );

        assertNotNull(results, "Search results container should exist");
        System.out.println("✅ Search results found!");
    }

    // -------------------------------------------------------
    // TEARDOWN - runs after each test
    // -------------------------------------------------------
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}