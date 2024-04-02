package com.itacademy.aqa.testng.factory;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class SimpleTest {
    @Test
    public void simpleTest() {
        System.out.println("Simple Test Method.");
        Assert.assertTrue(new Random().nextInt(10) < 3);
    }
    @Test
    public void simpleSecondTest() {
        System.out.println("Simple Test Method.");
        Assert.assertTrue(new Random().nextInt(10) < 3);
    }
}
