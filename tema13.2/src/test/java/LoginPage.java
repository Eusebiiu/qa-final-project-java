

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    // Localizatorul pentru link-ul "Register here!"
    @FindBy(linkText = "Register here!")
    private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Face click pe link-ul de înregistrare, navigând la RegisterPage.
     */
    public RegisterPage clickRegisterLink() {
        registerLink.click();
        return new RegisterPage(driver);
    }
}