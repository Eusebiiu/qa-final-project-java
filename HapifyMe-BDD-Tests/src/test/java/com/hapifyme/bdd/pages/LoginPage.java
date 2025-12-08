package com.hapifyme.bdd.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class LoginPage {

    private final SelenideElement emailField = $("#emailId");
    private final SelenideElement passwordField = $("#passwordId");
    private final SelenideElement loginButton = $("input[name='login_button']");
    private final SelenideElement errorMessage = $("#log_inv");

    public void openLoginPage() {
        open("/login_register.php");
    }

    public FeedPage attemptLogin(String email, String password) {
        emailField.shouldBe(Condition.visible).setValue(email);
        passwordField.shouldBe(Condition.visible).setValue(password);

        executeJavaScript("arguments[0].removeAttribute('required')", emailField);

        loginButton.click();

        return new FeedPage();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.shouldBe(Condition.visible).exists();
    }
}