package com.itacademy.aqa.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        dryRun = false,
        features = "src/test/resources/features", // feature files folder
        glue = {"com.itacademy.aqa.cucumber.stepdefs"}, //Step definitions java classes
        tags = "@DataTypesExample"
//        tags = "@Success"
//        tags = "@Success or @DataDriven"
)
public class CucumberRunner {

}
