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

  @positive @datatable
  Scenario: Login using DataTable credentials
    When I login with the following credentials:
      | username | standard_user           |
      | password | secret_sauce            |
      | email    | standart_user@gmail.com |
    Then I should be redirected to the home page

    # ==================== Scenario Outline ====================
  # CUCUMBER FEATURE: Scenario Outline + Examples
  # Runs the same scenario multiple times with different data
  # <placeholder> syntax references columns in Examples table

  @outline @negative
  Scenario Outline: Login fails with various invalid credentials: <username> and <password>
    When I login with username "<username>" and password "<password>"
    Then I should see an error message "<errorMessage>"

    # CUCUMBER FEATURE: Examples table
    # Each row creates a separate test scenario
    # Column headers match <placeholder> names in steps
    Examples: Invalid credentials
      | username      | password     | errorMessage                       |
      | invalid_user  | secret_sauce | Username and password do not match |
      | standard_user | wrong_pass   | Username and password do not match |
      |               | secret_sauce | Username is required               |
      | standard_user |              | Password is required               |

  @outline @positive
  Scenario Outline: Successful login with different valid users
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the home page

    @smoke
    Examples: Standard users
      | username                | password     |
      | standard_user           | secret_sauce |
      | performance_glitch_user | secret_sauce |

      # CUCUMBER FEATURE: Multiple Examples tables
  # Different tables can have different tags and descriptions
  @outline @positive
  Scenario Outline: Special Successful login with different valid users: <username> and <password>
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the home page

    @standard
    Examples: Standard users
      | username                | password     |
      | standard_user           | secret_sauce |
      | performance_glitch_user | secret_sauce |

    @special
    Examples: Special users
      | username   | password     |
      | error_user | secret_sauce |