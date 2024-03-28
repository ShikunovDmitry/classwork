package com.itacademy.aqa.testng;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Random;

public class TestNgTest {
    @Test(groups = {"smoke,regression"}, priority = 2)
    public void twoTest() {
        Assert.assertEquals(true, true, " True is false true");
    }

    @Test(groups = {"smoke,regression"}, priority = 1)
    public void threeTest() {
        Assert.assertEquals(true, true, " True is false true");
    }

    @Test(groups = "regression", description = "Just third test", dependsOnMethods = "simpleSecondTest",
            alwaysRun = true)
    public void thirdTest() {
        Assert.assertEquals(true, true, " True is not true");
    }

    @Test(groups = "smoke", description = "Just simple test", dependsOnMethods = "simpleSecondTest")
    public void simpleTest() {
        Assert.assertEquals(true, true, " True is not true");
    }

    @Test(groups = {"smoke,regression"})
    public void simpleSecondTest() {
        Assert.assertEquals(true, false, " True is false true");
    }

    @DataProvider(name = "platforms", parallel = true)
    public static Object[][] getPlatforms() {
        return new Object[][]{
                {"desktop", 1.0, true},
                {"android", 1.0, false},
                {"iphone", 1.0, true},
                {"ios", 1.0, true},
                {"web", 1.0, true}
        };
    }

    @Test(dataProvider = "platforms", groups = "mobile")
    public void testPlatforms(String platform, Double version, Boolean str) {
        System.out.println("Running in platform " + platform + " version: " + version);
        Assert.assertTrue(str);
    }

    @BeforeSuite
    public void beforeSuit() {
        System.out.println("before suit");
    }

    @AfterSuite
    public void afterSuit() {
        System.out.println("after suit");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before test");
    }

    @AfterMethod
    public void afterTest(ITestResult iTestResult) {
        System.out.println("After test");
        if (iTestResult.isSuccess()) {
            System.out.println("Test passed" + iTestResult.getMethod().getMethodName());
        } else {
            System.out.println("Test failed" + iTestResult.getMethod().getMethodName());
        }
    }

    @AfterTest
    public void afterTest(ITestContext iTestContext) {
        System.out.println("After test");
        iTestContext.getFailedTests().getAllResults();
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void flackyTest() {
        Assert.assertTrue(new Random().nextInt(10) < 3);
    }
}
