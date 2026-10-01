@login @smoke
Feature: User Authentication
  As a registered user
  I want to be able to login to the application
  So that I can access my account and shop

  # CUCUMBER FEATURE: Background
  # Background steps run before EACH scenario in this feature file
  # They are like a "before each" but written in Gherkin
  Background:
    Given I am on the login page

  @positive @critical
  Scenario: Successful login with valid credentials
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the home page
    And the page title should be "Products"

  @negative
  Scenario: Login fails with invalid username
    When I login with username "invalid_user" and password "secret_sauce"
    Then I should see an error message "Username and password do not match"
    And I should remain on the login page

  @negative
  Scenario: Login fails with invalid password
    When I login with username "standard_user" and password "wrong_password"
    Then I should see an error message "Username and password do not match"

  @negative
  Scenario: Login fails with empty credentials
    When I click the login button
    Then I should see an error message "Username is required"

  @negative
  Scenario: Login fails for locked out user
    When I login with username "locked_out_user" and password "secret_sauce"
    Then I should see an error message "Sorry, this user has been locked out"