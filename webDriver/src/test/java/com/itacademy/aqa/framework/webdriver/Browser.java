package com.itacademy.aqa.framework.webdriver;

import com.itacademy.aqa.framework.configuration.Configuration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Browser {
    static {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
    }

    private static int DEFAULT_WAIT_IN_SECONDS = 30;

    private static WebDriver driver;

    private Browser() {
    }

    public static void initDriver() {
        driver = BrowserFactory.createDriver(Configuration.getBrowserType());
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_IN_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static void takeSceenShot() {

        File sceenShots = new File(Configuration.getSceenShotFolder());

        if (!sceenShots.exists()) {
            sceenShots.mkdirs();
        }
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss-SS--a");
        String formattedDate = simpleDateFormat.format(date);

        String fileName = Configuration.getBrowserType() + "_" + formattedDate + "_sceenshot.png";

        byte[] srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

        try {
            Files.write(new File(Configuration.getSceenShotFolder() + fileName).toPath(), srcFile, StandardOpenOption.CREATE);
        } catch (IOException ex) {
            System.out.println("Can't save a file + " + fileName);
        }


    }

    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }
}
