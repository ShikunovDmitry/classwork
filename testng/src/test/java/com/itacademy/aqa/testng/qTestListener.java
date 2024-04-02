package com.itacademy.aqa.testng;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class qTestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getName() +
                " qTest was " + (result.isSuccess() ? "SUCCESS" : "FAILED"));

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " qTest was "
                + (result.isSuccess() ? "SUCCESS" : "FAILED"));
    }
}
