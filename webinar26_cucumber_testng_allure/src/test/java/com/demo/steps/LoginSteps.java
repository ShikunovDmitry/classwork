package com.demo.steps;

import com.demo.context.TestContext;
import com.demo.utils.AllureUtils;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;;

public class LoginSteps {

  private final TestContext context;
  public LoginSteps(TestContext context) {
    this.context = context;
  }

//  @Given("I am on the login page")
//  public void iAmOnTheLoginPage2() {
//    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
//    loginPage.open();
//    AllureUtils.step("Opened login page");
//  }

  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {
    context.getLoginPage().open();
    AllureUtils.step("Opened login page");
  }

//  @When("I login with username {string} and password {string}")
//  public void iLoginWithUsernameAndPassword(String username, String password) {
//    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
//    loginPage.login(username, password);
//    AllureUtils.step("Username: " + username);
//  }

  @When("I login with username {string} and password {string}")
  public void iLoginWithUsernameAndPassword(String username, String password) {
    context.getLoginPage().login(username, password);
    AllureUtils.step("Username: " + username);
  }

//  @Then("I should be redirected to the home page")
//  public void iShouldBeRedirectedToTheHomePage() {
//    HomePage homePage = new HomePage(DriverManager.getDriver());
//    Assertions.assertThat(homePage.isDisplayed())
//        .as("The home page should be displayed after login")
//        .isTrue();
//    AllureUtils.step("Verified home page is displayed");
//  }

  @Then("I should be redirected to the home page")
  public void iShouldBeRedirectedToTheHomePage() {
    Assertions.assertThat(context.getHomePage().isDisplayed())
        .as("The home page should be displayed after login")
        .isTrue();
    AllureUtils.step("Verified home page is displayed");
  }

  @Then("I should see an error message {string}")
  public void iShouldSeeErrorMessage(String expectedMessage) {
    Assertions.assertThat(context.getLoginPage().isErrorDisplayed())
        .as("Error message should be displayed")
        .isTrue();

    String actualMessage = context.getLoginPage().getErrorMessage();
    Assertions.assertThat(actualMessage)
        .as("Error message text should match")
        .contains(expectedMessage);

    AllureUtils.addParameter("Expected Error", expectedMessage);
    AllureUtils.addParameter("Actual Error", actualMessage);
  }

  @And("I should remain on the login page")
  public void iShouldRemainOnTheLoginPage() {
    Assertions.assertThat(context.getLoginPage().isOnLoginPage())
        .as("Should remain on login page")
        .isTrue();
  }

  @When("I click the login button")
  public void iClickTheLoginButton() {
    context.getLoginPage().clickLogin();
    AllureUtils.step("Clicked login button");
  }
}
