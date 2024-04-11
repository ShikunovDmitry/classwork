package com.itacademy.aqa.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    public static WebDriver createDriver(BrowserType browserType) {
        WebDriver driver;
        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", getPrefs());
                driver = new ChromeDriver(chromeOptions);
                break;
            case CHROME_EMULATOR:
                ChromeOptions mobileDeviceOptions = new ChromeOptions();
                Map<String, Object> browserProperties = new HashMap<>();
                //Option 1
//                browserProperties.put("deviceName","iPad Pro");
                //Option 2
                Map<String, Object> deviceMetrics = new HashMap<>();
                deviceMetrics.put("width", 360);
                deviceMetrics.put("height", 360);
                browserProperties.put("deviceMetrics", deviceMetrics);


                mobileDeviceOptions.setExperimentalOption("mobileEmulation", browserProperties);

                mobileDeviceOptions.setExperimentalOption("prefs", getPrefs());
                driver = new ChromeDriver(mobileDeviceOptions);
                break;
            default:
                throw new RuntimeException("Can't create a driver for " + browserType);
        }
        return driver;
    }

    private static Map<String, Object> getPrefs() {
        Map<String, Object> prefs = new HashMap<>();
//                prefs.put("profile.default_content_settings.popups", 0);
//                prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("browser.helperApps.alwaysAsk.force", false);
        prefs.put("download.default_directory", "C:\\Users\\Dzmitry_Shykunou\\Downloads\\");
        prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/csv;application/octet-stream;application/x-msdownload");
        return prefs;
    }
}
