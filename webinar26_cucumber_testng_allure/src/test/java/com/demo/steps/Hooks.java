package com.demo.steps;

import com.demo.config.DriverManager;
import com.demo.context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

  private final TestContext testContext;
  public Hooks(TestContext testContext) {
    this.testContext = testContext;
  }
  @Before
  public void initializeDriver() {
    DriverManager.initDriver();
  }

  @After
  public void tearDown() {
    DriverManager.quitDriver();
  }
}
