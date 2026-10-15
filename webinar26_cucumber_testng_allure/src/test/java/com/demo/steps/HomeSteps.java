package com.demo.steps;

import com.demo.context.TestContext;
import com.demo.utils.AllureUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HomeSteps {

  private static final Logger log = LoggerFactory.getLogger(HomeSteps.class);
  private final TestContext context;

  public HomeSteps(TestContext context) {
    this.context = context;
  }

  @Then("I should see the products page")
  public void iShouldSeeProductsPage() {
    Assertions.assertThat(context.getHomePage().isDisplayed())
        .as("Products page should be displayed")
        .isTrue();
  }

  @Then("the page title should be {string}")
  public void pageTitleShouldBe(String expectedTitle) {
    String actualTitle = context.getHomePage().getTitle();
    Assertions.assertThat(actualTitle)
        .as("Page title should be: " + expectedTitle)
        .isEqualTo(expectedTitle);
  }

  @Then("I should see {int} products")
  public void iShouldSeeProducts(int expectedCount) {
    int actualCount = context.getHomePage().getProductCount();
    Assertions.assertThat(actualCount)
        .as("Product count should be: " + expectedCount)
        .isEqualTo(expectedCount);
    AllureUtils.addParameter("Expected Products", String.valueOf(expectedCount));
    AllureUtils.addParameter("Actual Products", String.valueOf(actualCount));
  }

  @When("I sort products by {string}")
  public void iSortProductsBy(String sortOption) {
    log.info("Sorting products by: {}", sortOption);
    context.getHomePage().sortProductsBy(sortOption);
    context.setData("sortOption", sortOption);
  }

  @Then("products should be sorted by {string}")
  public void productsShouldBeSortedBy(String sortType) {
    List<String> productNames = context.getHomePage().getAllProductNames();

    switch (sortType.toLowerCase()) {
      case "name ascending" -> {
        List<String> sorted = productNames.stream().sorted().toList();
        Assertions.assertThat(productNames)
            .as("Products should be sorted A-Z")
            .isEqualTo(sorted);
      }
      case "name descending" -> {
        List<String> sorted = productNames.stream()
            .sorted(java.util.Comparator.reverseOrder()).toList();
        Assertions.assertThat(productNames)
            .as("Products should be sorted Z-A")
            .isEqualTo(sorted);
      }
      default -> log.warn("Unknown sort type: {}", sortType);
    }
  }

  @When("I add {string} to the cart")
  public void iAddProductToCart(String productName) {
    log.info("Adding product to cart: {}", productName);
    context.getHomePage().addProductToCart(productName);
    context.setData("lastAddedProduct", productName);
  }

  @When("I add the following products to the cart:")
  public void iAddProductsToCart(DataTable dataTable) {
    List<String> products = dataTable.asList();
    products.forEach(product -> {
      log.info("Adding to cart: {}", product);
      context.getHomePage().addProductToCart(product);
    });
    context.setData("addedProducts", products);
  }

  @Then("the cart badge should show {int}")
  public void cartBadgeShouldShow(int expectedCount) {
    int actualCount = context.getHomePage().getCartItemCount();
    Assertions.assertThat(actualCount)
        .as("Cart badge count should be: " + expectedCount)
        .isEqualTo(expectedCount);
  }

  @When("I navigate to the cart")
  public void iNavigateToCart() {
    context.getHomePage().goToCart();
  }

  @When("I click on product {string}")
  public void iClickOnProduct(String productName) {
    context.getHomePage().clickProduct(productName);
    context.setData("selectedProduct", productName);
  }

  @When("I logout")
  public void iLogout() {
    context.getHomePage().logout();
  }
}