@login @advanced
Feature: Advanced Login Scenarios
  Demonstrates advanced Cucumber features

    # CUCUMBER FEATURE: Custom @ParameterType
  # "standard" is converted to User object by UserTypeTransformer
  @positive @paramtype
  Scenario: Login as standard user using custom parameter type
    Given I am logged in as a standard user
    Then I should see the products page

      # ==================== DataTable - Multiple Rows ====================
  # CUCUMBER FEATURE: DataTable with multiple rows

  @datatable
  Scenario: Verify multiple user login attempts
    When I attempt login with multiple users:
      | username      | password     | expectedResult |
      | standard_user | secret_sauce | success        |
      | locked_out    | secret_sauce | failure        |
      | invalid_user  | wrong_pass   | failure        |

  @docstring
  Scenario: Login page displays correct content
    Then the page should contain the following text:
      """
      Swag Labs
      """