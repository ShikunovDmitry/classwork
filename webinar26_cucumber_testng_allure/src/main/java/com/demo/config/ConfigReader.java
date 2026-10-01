package com.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads configuration from properties file
 * Supports environment variable overrides
 */
public class ConfigReader {

  private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
  private static final Properties properties = new Properties();
  private static final String CONFIG_FILE = "config/config.properties";

  static {
    loadProperties();
  }

  private static void loadProperties() {
    try (InputStream input = ConfigReader.class
        .getClassLoader()
        .getResourceAsStream(CONFIG_FILE)) {
      if (input == null) {
        throw new RuntimeException("Config file not found: " + CONFIG_FILE);
      }
      properties.load(input);
      log.info("Configuration loaded from: {}", CONFIG_FILE);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration", e);
    }
  }

  /**
   * Get property value with environment variable override support
   * Environment variables take precedence over properties file
   */
  public static String get(String key) {
    // Check environment variable first (useful for CI/CD)
    String envValue = System.getenv(key.toUpperCase().replace(".", "_"));
    if (envValue != null && !envValue.isEmpty()) {
      return envValue;
    }
    // Check system property (can be passed via -D flag)
    String sysValue = System.getProperty(key);
    if (sysValue != null && !sysValue.isEmpty()) {
      return sysValue;
    }
    return properties.getProperty(key);
  }

  public static String get(String key, String defaultValue) {
    String value = get(key);
    return (value != null && !value.isEmpty()) ? value : defaultValue;
  }

  public static String getBaseUrl() {
    return get("base.url");
  }
}