package com.demo.steps;

import com.demo.context.TestContext;
import com.demo.utils.AllureUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductSteps {

  private static final Logger log = LoggerFactory.getLogger(ProductSteps.class);
  private final TestContext context;

  public ProductSteps(TestContext context) {
    this.context = context;
  }

  @Then("I should see product name {string}")
  public void iShouldSeeProductName(String expectedName) {
    String actualName = context.getProductPage().getProductName();
    Assertions.assertThat(actualName)
        .as("Product name should be: " + expectedName)
        .isEqualTo(expectedName);
  }

  @Then("I should see product price {string}")
  public void iShouldSeeProductPrice(String expectedPrice) {
    String actualPrice = context.getProductPage().getProductPrice();
    Assertions.assertThat(actualPrice)
        .as("Product price should be: " + expectedPrice)
        .isEqualTo(expectedPrice);
    AllureUtils.addParameter("Product Price", actualPrice);
  }

  @When("I add the product to cart from product page")
  public void iAddProductToCartFromProductPage() {
    String productName = context.getProductPage().getProductName();
    context.getProductPage().addToCart();
    context.setData("addedFromDetailPage", productName);
    log.info("Added product from detail page: {}", productName);
  }

  @Then("the add to cart button should be visible")
  public void addToCartButtonShouldBeVisible() {
    Assertions.assertThat(context.getProductPage().isAddToCartButtonDisplayed())
        .as("Add to cart button should be visible")
        .isTrue();
  }

  @Then("the remove button should be visible")
  public void removeButtonShouldBeVisible() {
    Assertions.assertThat(context.getProductPage().isRemoveButtonDisplayed())
        .as("Remove button should be visible")
        .isTrue();
  }

  @When("I go back to products")
  public void iGoBackToProducts() {
    context.getProductPage().goBack();
  }
}