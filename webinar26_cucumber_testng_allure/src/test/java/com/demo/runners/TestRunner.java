package com.demo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * CUCUMBER FEATURE: CucumberOptions Configuration
 *
 * Main test runner - runs all features
 *
 * @CucumberOptions parameters:
 *
 * features    - Path to feature files
 * glue        - Package(s) containing step definitions and hooks
 * tags        - Filter scenarios by tags (expressions supported)
 * plugin      - Output formatters/reporters
 * monochrome  - Clean console output (no ANSI codes)
 * dryRun      - Validate step definitions without executing
 * publish     - Publish results to Cucumber Reports service
 * snippets    - Style for generated step snippets
 */
@CucumberOptions(
    // Feature files location
    features = "src/test/resources/features",

    // Step definitions, hooks, and transformers packages
    glue = {
        "com.demo.steps",
        "com.demo.transformers"
    },

    // Tag expression - run all except @wip and @manual
    tags = "not @wip and not @manual",
//    tags = "@login",

    // Plugins/Reporters
    plugin = {
        // Pretty console output
        "pretty",

        // HTML report
        "html:target/cucumber-reports/cucumber.html",

        // JSON report (used by CI tools)
        "json:target/cucumber-reports/cucumber.json",

        // JUnit XML report
        "junit:target/cucumber-reports/cucumber.xml",

        // Allure Cucumber integration
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",

        // Timeline report
        "timeline:target/cucumber-reports/timeline",

        // Rerun failed tests
        "rerun:target/rerun.txt"
    },

    // Clean console output
    monochrome = false,

    // Validate step definitions exist (set true to check without running)
    dryRun = false,

    // Snippet style for undefined steps
    snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class TestRunner extends AbstractTestNGCucumberTests {

  /**
   * CUCUMBER FEATURE: Parallel Execution
   *
   * Override dataProvider to enable parallel scenario execution.
   * parallel = true runs scenarios in parallel using TestNG's thread pool.
   *
   * Thread count is configured in testng.xml via the Maven property `${testng.thread.count}`
   */
  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}