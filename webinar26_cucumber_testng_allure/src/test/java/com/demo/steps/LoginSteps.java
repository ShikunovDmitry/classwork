package com.demo.steps;

import com.demo.context.TestContext;
import com.demo.models.User;
import com.demo.utils.AllureUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class LoginSteps {

  protected final Logger log = LoggerFactory.getLogger(getClass());
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

  @When("I login with the following credentials:")
  public void iLoginWithTheFollowingCredentials(DataTable dataTable) {
    Map<String, String> credentials = dataTable.asMap(String.class, String.class);
    String username = credentials.get("username");
    String password = credentials.get("password");
    String email = credentials.get("email");
    log.info("Logging in with DataTable credentials: {}", username);
    context.getLoginPage().login(username, password);
    context.setData("username", username);
    context.setData("email", email);
    log.info("Logging in with DataTable credentials: {}", username);
  }

  /**
   * CUCUMBER FEATURE: Custom @ParameterType
   * The "userType" parameter type is defined in UserTypeTransformer
   * Cucumber automatically converts "standard" -> User object
   */
  @Given("I am logged in as a {userType} user")
  public void iAmLoggedInAsUser(User user) {
    log.info("Logging in as: {}", user);
    context.getLoginPage().open();
    context.getLoginPage().login(user.getUsername(), user.getPassword());
    context.setData("currentUser", user);
    AllureUtils.addParameter("User Type", user.getRole());
  }

  @When("I attempt login with multiple users:")
  public void iAttemptLoginWithMultipleUsers(DataTable dataTable) {
    List<Map<String, String>> users = dataTable.asMaps();

    users.forEach(userRow -> {
      String username = userRow.get("username");
      String password = userRow.get("password");
      String expected = userRow.get("expectedResult");

      log.info("Testing login for: {} (expected: {})", username, expected);
      context.getLoginPage().open();
      context.getLoginPage().login(username, password);

      // Store results for verification
      context.setData("lastLoginResult_" + username,
          context.getLoginPage().isErrorDisplayed() ? "failure" : "success");
      //for example we can retrieve data in different step as the follows:
      String actual =  context.getData("lastLoginResult_" + username);

      Assertions.assertThat(actual)
          .as("Result should match")
          .isEqualTo(expected);
    });
  }
}
