package com.hapifyme.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegisterPage
{
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "reg_fname")
    private WebElement firstNameInput;
    // ... alte @FindBy-uri ...
    @FindBy(name = "register_button")
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Completează formularul și trimite.
     */
    public void registerUser(String firstName, String lastName, String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));

        firstNameInput.sendKeys(firstName);
        // ... restul câmpurilor ...
        registerButton.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}