
@products @authenticated
Feature: Product Catalog
  As a logged-in user
  I want to browse and interact with products
  So that I can find items to purchase

  # @authenticated tag triggers the pre-login hook in Hooks.java
  # No need to repeat login steps in each scenario!

  Background:
    Given I am on the login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should see the products page

  @smoke
  Scenario: View all products on home page
    Then I should see 6 products
    And the page title should be "Products"

  @sorting
  Scenario Outline: Sort products by different criteria
    When I sort products by "<sortOption>"
    Then products should be sorted by "<sortType>"

    Examples:
      | sortOption          | sortType       |
      | Name (A to Z)       | name ascending |
      | Name (Z to A)       | name descending|

  @product_detail
  Scenario: View product details
    When I click on product "Sauce Labs Backpack"
    Then I should see product name "Sauce Labs Backpack"
    And I should see product price "$29.99"
    And the add to cart button should be visible

  @add_to_cart
  Scenario: Add single product to cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1

  @add_to_cart
  Scenario: Add multiple products to cart
    When I add the following products to the cart:
      | Sauce Labs Backpack    |
      | Sauce Labs Bike Light  |
      | Sauce Labs Bolt T-Shirt|
    Then the cart badge should show 3

  @product_detail @add_to_cart
  Scenario: Add product to cart from product detail page
    When I click on product "Sauce Labs Fleece Jacket"
    And I add the product to cart from product page
    Then the remove button should be visible
    When I go back to products
    Then the cart badge should show 1