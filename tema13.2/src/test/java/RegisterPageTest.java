


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterPageTest {

    private WebDriver driver;
    private RegisterPage registerPage;

    // --- Date de test ---
    // !!! SCHIMBAȚI CU URL-ul PAGINII DE LOGIN !!!
    private final String URL_LOGIN = "https://test.hapifyme.com/login_register.php";

    private final String VALID_FIRST_NAME = "Tester";
    private final String VALID_LAST_NAME = "Automation";
    private final String VALID_EMAIL = "testuser" + System.currentTimeMillis() + "@email.com";
    private final String VALID_PASSWORD = "Password123!";
    // --------------------

    @BeforeMethod
    public void setup() {
        // 1. Inițializează WebDriver
       // driver = DriverFactory.getDriver();

        // 2. Navighează la pagina de Login
        driver.get(URL_LOGIN);

        // 3. Face click pe link-ul Register și obține RegisterPage
        LoginPage loginPage = new LoginPage(driver);
        registerPage = loginPage.clickRegisterLink();
    }

    @Test
    public void testSuccessfulRegistration() {
        System.out.println("Rulează testul: Înregistrare Reușită");

        // 1. Acțiune: Apelarea metodei registerUser
        registerPage.registerUser(
                VALID_FIRST_NAME,
                VALID_LAST_NAME,
                VALID_EMAIL,
                VALID_PASSWORD
        );

        // 2. Verificare (Assertion):
        // !!! SCHIMBAȚI PARTEA DE URL AȘTEPTATĂ DUPĂ ÎNREGISTRARE (ex: /home sau /succes) !!!
        String expectedUrlPart = "/profilul-meu";
        String actualUrl = registerPage.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains(expectedUrlPart),
                "Eroare: După înregistrare, URL-ul nu conține: " + expectedUrlPart + ". URL-ul curent este: " + actualUrl);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}