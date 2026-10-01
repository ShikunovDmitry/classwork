package com.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

  private static final By PAGE_TITLE = By.cssSelector(".title");
  private static final By PRODUCT_ITEMS = By.cssSelector(".inventory_item");
  private static final By PRODUCT_NAMES = By.cssSelector(".inventory_item_name");
  private static final By SORT_DROPDOWN = By.cssSelector("[data-test='product_sort_container']");
  private static final By SHOPPING_CART = By.cssSelector(".shopping_cart_link");
  private static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");
  private static final By BURGER_MENU = By.id("react-burger-menu-btn");
  private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
  private static final By ADD_TO_CART_BUTTON = By.cssSelector("[data-test^='add-to-cart']");

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @Step("Verify home page is displayed")
  public boolean isDisplayed() {
    return isDisplayed(PAGE_TITLE) &&
        getText(PAGE_TITLE).equals("Products");
  }

  @Step("Get page title")
  public String getTitle() {
    return getText(PAGE_TITLE);
  }

  @Step("Get all product names")
  public List<String> getAllProductNames() {
    return findElements(PRODUCT_NAMES)
        .stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  @Step("Get product count")
  public int getProductCount() {
    return countElements(PRODUCT_ITEMS);
  }

  @Step("Sort products by: {sortOption}")
  public void sortProductsBy(String sortOption) {
    selectByVisibleText(SORT_DROPDOWN, sortOption);
  }

  @Step("Add product to cart by name: {productName}")
  public void addProductToCart(String productName) {
    By addButton = By.xpath(
        String.format("//div[text()='%s']" +
                "/ancestor::div[@class='inventory_item']" +
                "//button[contains(@data-test,'add-to-cart')]",
            productName));
    click(addButton);
  }

  @Step("Get cart item count")
  public int getCartItemCount() {
    if (!isDisplayed(CART_BADGE)) return 0;
    return Integer.parseInt(getText(CART_BADGE));
  }

  @Step("Navigate to shopping cart")
  public void goToCart() {
    click(SHOPPING_CART);
  }

  @Step("Logout from application")
  public void logout() {
    click(BURGER_MENU);
    click(LOGOUT_LINK);
  }

  @Step("Click on product: {productName}")
  public void clickProduct(String productName) {
    By productLink = By.xpath(
        String.format("//div[@class='inventory_item_name' and text()='%s']",
            productName));
    click(productLink);
  }
}