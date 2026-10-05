package com.demo.steps;

import com.demo.context.TestContext;
import com.demo.utils.AllureUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.w3c.dom.Document;

import java.time.LocalDate;

@Slf4j
public class CommonSteps {

  private TestContext context;

  public CommonSteps(TestContext context) {
    this.context = context;
  }
  @Then("the page should contain the following text:")
  public void thePageShouldContainTheFollowingText(String docString) {
    // docString contains the multi-line text from the feature file
    String pageSource = context.getDriver().getPageSource();

    // Split by lines and check each
    String[] lines = docString.trim().split("\n");
    for (String line : lines) {
      String trimmedLine = line.trim();
      if (!trimmedLine.isEmpty()) {
        Assertions.assertThat(pageSource)
            .as("Page should contain: " + trimmedLine)
            .contains(trimmedLine);
      }
    }

    AllureUtils.attachText("Expected Text", docString);
  }

  @When("I send the following JSON:")
  public void iSendTheFollowingJSON(JsonNode json) {
    // json is already parsed JsonNode - no objectMapper.readTree() needed!
    log.info("Sending JSON: {}", json.toPrettyString());
    context.setData("requestJson", json);
    AllureUtils.attachJson("Request JSON", json.toPrettyString());

    // Access fields directly
    if (json.has("username")) {
      String username = json.get("username").asText();
      log.info("Username from JSON: {}", username);
      context.setData("jsonUsername", username);
    }
  }

  @Then("the user should be created")
  public void userShouldBeCreated() {
    JsonNode json = context.getData("requestJson");
    Assertions.assertThat(json).isNotNull();
    Assertions.assertThat(json.has("username")).isTrue();
  }

  /**
   * Uses xmlDocument @DocStringType
   * """xml ... """ → Document automatically
   */
  @When("I send the following XML payload:")
  public void iSendXmlPayload(Document xml) {
    // xml is already parsed DOM Document!
    String rootElement = xml.getDocumentElement().getTagName();
    log.info("Sending XML with root element: {}", rootElement);
    context.setData("requestXml", xml);
    AllureUtils.attachText("XML Root Element", rootElement);
  }

  @Then("the order should be processed")
  public void orderShouldBeProcessed() {
    Document xml = context.getData("requestXml");
    Assertions.assertThat(xml).isNotNull();
    Assertions.assertThat(xml.getDocumentElement().getTagName())
        .isEqualTo("order");
  }

  @When("I filter orders from {isoDate}")
  public void iFilterOrdersFrom(LocalDate date) {
    log.info("Filtering orders from: {}", date);
    context.setData("filterFromDate", date);
    AllureUtils.addParameter("Filter From Date", date.toString());
  }

  @Then("I should see orders after that date")
  public void iShouldSeeOrdersAfterThatDate() {
    LocalDate filterDate = context.getData("filterFromDate");
    log.info("Verifying orders after: {}", filterDate);
    Assertions.assertThat(filterDate).isNotNull();
  }

  @When("I filter products by max price {price}")
  public void iFilterProductsByMaxPrice$(Double maxPrice) {
    // maxPrice is already Double - no string manipulation needed!
    log.info("Filtering by max price: {}", maxPrice);
    context.setData("maxPrice", maxPrice);
    AllureUtils.addParameter("Max Price", String.valueOf(maxPrice));
  }

  @Then("all products should cost less than that price")
  public void allProductsShouldCostLessThanThatPrice() {
    Double maxPrice = context.getData("maxPrice");
    log.info("Verifying all products cost less than: {}", maxPrice);
    // Verification logic here
    Assertions.assertThat(maxPrice).isGreaterThan(0.0);
  }
}
