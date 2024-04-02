package com.itacademy.aqa.testng.factory.parameters;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleParametersTest {
    private int param;
    public SimpleParametersTest(int param){
        this.param = param;
    }
    @Test
    public void simpleTest() {
        System.out.println("Simple Test Method.");
        Assert.assertTrue(param < 3);
    }
}
