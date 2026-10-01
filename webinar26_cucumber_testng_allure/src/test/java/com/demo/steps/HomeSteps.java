package com.demo.steps;

import com.demo.config.DriverManager;
import com.demo.context.TestContext;
import com.demo.pages.HomePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import org.assertj.core.api.Assertions;

public class HomeSteps {
  private final TestContext testContext;

  public HomeSteps(TestContext testContext) {
    this.testContext = testContext;
  }

//  @And("the page title should be {string}")
//  public void thePageTitleShouldBe(String expectedTitle) {
//    HomePage homePage = new HomePage(DriverManager.getDriver());
//    String actualTitle = homePage.getTitle();
//    Assertions.assertThat(actualTitle)
//        .as("The page title should be " + expectedTitle + " after login")
//        .isEqualTo(expectedTitle);
//  }
    @And("the page title should be {string}")
  public void thePageTitleShouldBe(String expectedTitle) {
    String actualTitle = testContext.getHomePage().getTitle();
    Assertions.assertThat(actualTitle)
        .as("The page title should be " + expectedTitle + " after login")
        .isEqualTo(expectedTitle);
  }
}
