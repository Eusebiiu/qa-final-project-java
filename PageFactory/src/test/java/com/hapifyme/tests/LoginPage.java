package com.hapifyme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(linkText = "Register here!")
    private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Face click pe link-ul de înregistrare.
     */
    public RegisterPage clickRegisterLink() {
        registerLink.click();
        return new RegisterPage(driver);
    }
}