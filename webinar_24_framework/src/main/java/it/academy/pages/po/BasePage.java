package it.academy.pages.po;

import it.academy.utilities.ConfigReader;

public abstract class BasePage {
  protected final String baseUrl = ConfigReader.getProperty("baseUrl");
}
