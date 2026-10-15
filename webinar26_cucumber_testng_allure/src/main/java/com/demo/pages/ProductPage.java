package com.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

  private static final By PRODUCT_NAME = By.cssSelector(".inventory_details_name");
  private static final By PRODUCT_DESCRIPTION = By.cssSelector(".inventory_details_desc");
  private static final By PRODUCT_PRICE = By.cssSelector(".inventory_details_price");
  private static final By ADD_TO_CART_BUTTON = By.cssSelector("[data-test^='add-to-cart']");
  private static final By REMOVE_BUTTON = By.cssSelector("[data-test^='remove']");
  private static final By BACK_BUTTON = By.id("back-to-products");

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  @Step("Get product name")
  public String getProductName() {
    return getText(PRODUCT_NAME);
  }

  @Step("Get product price")
  public String getProductPrice() {
    return getText(PRODUCT_PRICE);
  }

  @Step("Get product description")
  public String getProductDescription() {
    return getText(PRODUCT_DESCRIPTION);
  }

  @Step("Add product to cart from product page")
  public void addToCart() {
    click(ADD_TO_CART_BUTTON);
  }

  @Step("Remove product from cart")
  public void removeFromCart() {
    click(REMOVE_BUTTON);
  }

  @Step("Go back to products list")
  public void goBack() {
    click(BACK_BUTTON);
  }

  public boolean isAddToCartButtonDisplayed() {
    return isDisplayed(ADD_TO_CART_BUTTON);
  }

  public boolean isRemoveButtonDisplayed() {
    return isDisplayed(REMOVE_BUTTON);
  }
}