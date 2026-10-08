@transformers
Feature: Transformer Demonstrations
  Shows all transformer types in action

  @doc_string_type
  Scenario: JSON DocString transformer
    When I send the following JSON:
      """json
      {
        "username": "john_doe",
        "email": "john@example.com",
        "role": "standard"
      }
      """
    Then the user should be created

  @doc_string_type
  Scenario: XML DocString transformer
    When I send the following XML payload:
      """xml
      <order>
        <product>Sauce Labs Backpack</product>
        <quantity>2</quantity>
        <price>29.99</price>
      </order>
      """
    Then the order should be processed

  @parameter_type
  Scenario: Date transformer - ISO format
    When I filter orders from 2024-01-15
    Then I should see orders after that date

  @parameter_type
  Scenario: Price transformer
    When I filter products by max price $50.00
    Then all products should cost less than that price