package com.demo.transformers;

import com.demo.models.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TypeTransformers {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Transforms user type string to User object
   * Usage in feature: Given I am logged in as a "standard" user
   * Usage in step: public void iAmLoggedInAs(User user)
   */
  //I am logged in as a standard|locked|problem|performance_glitch|error user
  @ParameterType("standard|locked|problem|performance_glitch|error")
  public User userType(String userType) {
    return switch (userType.toLowerCase()) {
      case "standard" -> User.standardUser();
      case "locked" -> User.lockedOutUser();
      case "problem" -> User.problemUser();
      case "performance_glitch" -> User.builder()
          .username("performance_glitch_user")
          .password("secret_sauce")
          .role("performance_glitch")
          .build();
      case "error" -> User.builder()
          .username("error_user")
          .password("secret_sauce")
          .role("error")
          .build();
      default -> throw new IllegalArgumentException(
          "Unknown user type: " + userType);
    };
  }

  @DocStringType(contentType = "json")
  public JsonNode jsonNode(String docString) {
    try {
      return objectMapper.readTree(docString);
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid JSON in DocString: " + e.getMessage(), e);
    }
  }

  /* Step definition:
   *   public void iSendXml(org.w3c.dom.Document xml)
   */
  @DocStringType(contentType = "xml")
  public Document xmlDocument(String docString) {
    try {
      DocumentBuilderFactory factory =
          DocumentBuilderFactory.newInstance();
      DocumentBuilder builder =
          factory.newDocumentBuilder();
      return builder.parse(
          new InputSource(new java.io.StringReader(docString)));
    } catch (Exception e) {
      throw new IllegalArgumentException(
          "Invalid XML in DocString: " + e.getMessage(), e);
    }
  }

  /**
   * Converts date strings to LocalDate
   * Supports multiple formats
   * <p>
   * Usage: When I filter orders from "2024-01-15"
   * Usage: When I filter orders from "15/01/2024"
   * Usage: When I filter orders from "January 15, 2024"
   */
  @ParameterType("\\d{4}-\\d{2}-\\d{2}|\\d{2}/\\d{2}/\\d{4}|[A-Za-z]+ \\d{1,2}, \\d{4}")
  public LocalDate isoDate(String dateString) {
    List<DateTimeFormatter> formatters = Arrays.asList(
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
    );
    for (DateTimeFormatter formatter : formatters) {
      try {
        return LocalDate.parse(dateString, formatter);
      } catch (DateTimeParseException ignored) {
        // Try next formatter
      }
    }
    throw new IllegalArgumentException("Cannot parse date: " + dateString);
  }

  @ParameterType("[$€£¥]\\d+\\.\\d{2}")
  public Double price(String priceString) {
    // Remove currency symbol and parse
    String numericValue = priceString.replaceAll("[^\\d.]", "");
    return Double.parseDouble(numericValue);
  }
}