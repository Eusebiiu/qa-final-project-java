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

    // 1. Declararea elementelor
    @FindBy(name = "reg_fname")
    private WebElement firstNameInput;

    @FindBy(name = "reg_lname")
    private WebElement lastNameInput;

    @FindBy(name = "reg_email")
    private WebElement emailInput;

    @FindBy(name = "reg_email2")
    private WebElement emailConfirmInput;

    @FindBy(id = "reg_password")
    private WebElement passwordInput;

    @FindBy(id = "reg_password2")
    private WebElement passwordConfirmInput;

    @FindBy(name = "register_button")
    private WebElement registerButton;


    // 2. Constructorul cu inițializare
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        // Așteptare explicită de 20 de secunde
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    // 3. Metode de acțiune și business

    /**
     * Completează formularul cu date valide și trimite.
     */
    public void registerUser(String firstName, String lastName, String email, String password) {
        // Așteaptă ca elementul să devină vizibil înainte de a interacționa
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        emailConfirmInput.sendKeys(email);
        passwordInput.sendKeys(password);
        passwordConfirmInput.sendKeys(password);
        registerButton.click();
    }

    /**
     * Obține URL-ul curent al paginii.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}