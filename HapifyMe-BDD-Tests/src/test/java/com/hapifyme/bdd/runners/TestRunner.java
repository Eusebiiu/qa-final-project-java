package com.hapifyme.bdd.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.hapifyme.bdd.stepdefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true
)
public class TestRunner {
}