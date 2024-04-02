package com.itacademy.aqa.testng.factory.parameters;

import org.testng.annotations.Factory;

public class SimpleParametersFactoryTest {
    @Factory
    public Object[] factoryMethod() {
        return new Object[] { new SimpleParametersTest(2), new SimpleParametersTest(6) };
    }
}
