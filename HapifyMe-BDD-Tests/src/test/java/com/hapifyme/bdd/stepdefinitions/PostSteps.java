package com.hapifyme.bdd.stepdefinitions;

import com.hapifyme.bdd.pages.LoginPage;
import com.hapifyme.bdd.pages.FeedPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertTrue;

public class PostSteps {

    private LoginPage loginPage = new LoginPage();
    private FeedPage feedPage;

    private final String VALID_EMAIL = "eusebiiumihalache@gmail.com";
    private final String VALID_PASSWORD = "Lastweek12@";


    @Given("sunt logat ca utilizator valid")
    public void sunt_logat_ca_utilizator_valid() {
        loginPage.openLoginPage();

        feedPage = loginPage.attemptLogin(VALID_EMAIL, VALID_PASSWORD);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String currentUrl = url();

        boolean isUrlCorrect = currentUrl.contains("index.php");
        boolean isElementVisible = feedPage.isUserLoggedIn();

        assertTrue("Logarea a eșuat. URL-ul actual (" + currentUrl + ") nu conține 'index.php' SAU elementul de feed lipsește.",
                isUrlCorrect && isElementVisible);
    }

    @And("sunt pe pagina principala \\(index.php)")
    public void sunt_pe_pagina_principalDa_index_php() {
        assertTrue("Nu s-a ajuns pe pagina Index.php.", url().contains("index.php"));
    }


    @When("scriu un mesaj in campul de postare: {string}")
    public void scriu_un_mesaj_in_campul_de_postare(String message) {
        if (feedPage == null) {
            feedPage = new FeedPage();
        }

        feedPage.createNewPost(message);
    }

    @When("apas butonul de Publicare")
    public void apas_butonul_de_publicare() {

    }


    @Then("postarea mea ar trebui sa apara in partea de sus a Feed-ului")
    public void postarea_mea_ar_trebui_sa_apara_in_partea_de_sus_a_feed_ului() {
        String expectedMessage = "Test Automation cu Selenide si Cucumber!";

        if (feedPage == null) {
            feedPage = new FeedPage();
        }

        feedPage.verifyPostContent(expectedMessage);
    }
}
