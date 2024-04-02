package com.itacademy.aqa.testng.factory;

import org.testng.annotations.Factory;

public class SimpleFactoryTest {
    @Factory
    public Object[] factoryMethod() {
        return new Object[] { new SimpleTest(), new SimpleTest() };
    }
}
