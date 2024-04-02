package com.itacademy.aqa.testng;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestRailListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() +
                " TestRail was " + (result.isSuccess() ? "SUCCESS" : "FAILED"));
        System.out.println(result.getMethod().getDescription());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " TestRail was "
                + (result.isSuccess() ? "SUCCESS" : "FAILED"));
    }
}
