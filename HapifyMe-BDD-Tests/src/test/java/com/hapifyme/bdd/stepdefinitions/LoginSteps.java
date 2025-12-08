package com.hapifyme.bdd.stepdefinitions;

import com.hapifyme.bdd.pages.LoginPage;
import com.hapifyme.bdd.pages.FeedPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage();
    private FeedPage feedPage;

    @Given("sunt pe pagina de login a aplicatiei")
    public void sunt_pe_pagina_de_login_a_aplicatiei() {
        loginPage.openLoginPage();
    }

    @When("ma autentific ca {string} cu parola {string}")
    public void ma_autentific_cu_succes(String email, String parola) {
        feedPage = loginPage.attemptLogin(email, parola);
    }

    @When("incerc sa ma autentific ca {string} cu parola {string}")
    public void incerc_sa_ma_autentific_cu_date_invalide(String email, String parola) {
        if (email.equalsIgnoreCase("blank")) {
            email = "";
        }
        if (parola.equalsIgnoreCase("blank")) {
            parola = "";
        }
        loginPage.attemptLogin(email, parola);
    }

    @Then("ar trebui sa fiu logat cu succes si sa vad pagina de Feed")
    public void ar_trebui_sa_fiu_logat_cu_succes() {
        String currentUrl = url();
        assertTrue("Login-ul nu a avut succes. Nu s-a ajuns pe pagina de Feed (URL-ul este: " + currentUrl + ")",
                currentUrl.contains("index.php"));
    }

    @Then("ar trebui sa vad un mesaj de eroare")
    public void ar_trebui_sa_vad_un_mesaj_de_eroare() {
        assertFalse("Login-ul a reușit în mod neașteptat.", new FeedPage().isUserLoggedIn());
        assertTrue("Mesajul de eroare nu este vizibil.", loginPage.isErrorMessageDisplayed());
    }
}