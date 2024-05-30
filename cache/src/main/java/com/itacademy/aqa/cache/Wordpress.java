package com.itacademy.aqa.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Wordpress {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin");

        File previousCookies = new File("src/cookies");
        if (previousCookies.exists()) {
            ByteArrayInputStream bis = new ByteArrayInputStream(FileUtils.readFileToByteArray(previousCookies));
            Object obj;
            try (ObjectInput in = new ObjectInputStream(bis)) {
                obj =  in.readObject();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            Set<Cookie> previousCookie = ((Set<Cookie>)obj);
            previousCookie.forEach(cookie -> webDriver.manage().addCookie(cookie));
        } else {
            webDriver.findElement(By.id("user_login")).sendKeys("admin");
            webDriver.findElement(By.id("user_pass")).sendKeys("kiyF5Oc#*8iE9DKx8bACg2DR");
            webDriver.findElement(By.id("wp-submit")).submit();
            Set<Cookie> cookieSet = webDriver.manage().getCookies();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] data;
            try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
                out.writeObject(cookieSet);
                out.flush();
                data = bos.toByteArray();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            FileUtils.writeByteArrayToFile(new File("src/cookies"), data);
        }

        webDriver.get("https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php?post_type=page");

        webDriver.quit();
        System.out.println("Exit driver");
    }
}
