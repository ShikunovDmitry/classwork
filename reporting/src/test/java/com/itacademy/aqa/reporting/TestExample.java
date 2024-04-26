package com.itacademy.aqa.reporting;

import io.qameta.allure.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class TestExample {

    private Logger logger = Logger.getLogger(this.getClass());

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void testExample2(){
        logger.info("Starting new test case example");
        Page page = new Page();
        page.open();
        Assert.assertTrue(page.isOpened());
        page.clickByElement("myElement");
        Assert.assertTrue(page.isElementExist("myElement"));
        logger.info("Test case finished");
    }
    @Test @TmsLink("DSC-3456") @Severity(SeverityLevel.BLOCKER)
    public void testExample(){
        logger.info("Starting new test case example");
        Page page = new Page();
        page.open();
        Assert.assertTrue(page.isOpened());
        page.clickByElement("myElement");
        Assert.assertTrue(page.isElementExist("myElement"));
        logger.info("Test case finished");
        ScreenShotMaker.makeScreenSot();
    }
    @Test @Link("DSC-2234") @Issue("DSC-1234") @Severity(SeverityLevel.MINOR)
    public void testExample3(){
        logger.info("Starting new test case example");
        Page page = new Page();
        page.open();
        Assert.assertTrue(page.isOpened());
        page.clickByElement("myElement");
        Assert.assertTrue(page.isElementExist("myElement"));
        logger.info("Test case finished");
    }
    @Test @Flaky @Description("This is flaky test") @Severity(SeverityLevel.NORMAL)
    public void testExample4() throws FileNotFoundException {
        logger.info("Starting new test case example");
        Page page = new Page();
        page.open();
        FileInputStream fis = new FileInputStream("src/test/resources/screenshot.png");
        Allure.addAttachment("Screenshot",fis);
        Assert.assertFalse(page.isOpened());
        page.clickByElement("myElement");
        Assert.assertTrue(page.isElementExist("myElement"));
        logger.info("Test case finished");
    }
}
