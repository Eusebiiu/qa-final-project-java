package com.hapifyme.bdd.stepdefinitions;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hooks {

    @Before
    public void setup() {
        Configuration.baseUrl = "https://test.hapifyme.com/";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1366x768";
    }

    @After
    public void teardown() {
        closeWebDriver();
    }
}