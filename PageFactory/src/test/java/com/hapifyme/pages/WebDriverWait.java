//package com.hapifyme.pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import java.time.Duration;
//
////public class WebDriverWait
//{
//    private WebDriver driver;
//    private org.openqa.selenium.support.ui.WebDriverWait wait;
//
//    // 1. Declararea elementelor
//    @FindBy(name = "reg_fname")
//    private WebElement firstNameInput;
//
//    @FindBy(name = "reg_lname")
//    private WebElement lastNameInput;
//
//    @FindBy(name = "reg_email")
//    private WebElement emailInput;
//
//    @FindBy(name = "reg_email2")
//    private WebElement emailConfirmInput;
//
//    @FindBy(id = "reg_password")
//    private WebElement passwordInput;
//
//    @FindBy(id = "reg_password2")
//    private WebElement passwordConfirmInput;
//
//    @FindBy(name = "register_button")
//    private WebElement registerButton;
//
//    @FindBy(id = "signin")
//    private WebElement signinLink;
//
//
//    // 2. Constructorul cu inițializare
//    public WebDriverWait(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        // Inițializăm WebDriverWait aici, cu un timeout de 10 secunde
//        this.wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));
//    }
//
//
//    // 3. Metode de acțiune și business
//
//    /**
//     * Completează formularul cu date valide (email-uri și parole identice)
//     */
//    public void registerUser(String firstName, String lastName, String email, String password) {
//        // Așteaptă ca elementul firstNameInput să fie vizibil și interacționabil
//        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
//
//        firstNameInput.sendKeys(firstName);
//        lastNameInput.sendKeys(lastName);
//        emailInput.sendKeys(email);
//        emailConfirmInput.sendKeys(email); // Email-ul este același
//        passwordInput.sendKeys(password);
//        passwordConfirmInput.sendKeys(password); // Parola este aceeași
//        registerButton.click();
//    }
//
//    /**
//     * Obține URL-ul curent al paginii.
//     */
//    public String getCurrentUrl() {
//        return driver.getCurrentUrl();
//    }
//}